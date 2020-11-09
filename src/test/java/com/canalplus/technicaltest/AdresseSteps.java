package com.canalplus.technicaltest;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.configureFor;
import static com.github.tomakehurst.wiremock.client.WireMock.containing;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.put;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.params.HttpParams;

import com.canalplus.technicaltest.model.dto.AbonneDto;
import com.canalplus.technicaltest.model.dto.AdresseDto;
import com.canalplus.technicaltest.model.dto.ConseillerDto;
import com.canalplus.technicaltest.model.dto.ContratDto;
import com.canalplus.technicaltest.model.dto.MouvementDto;
import com.canalplus.technicaltest.model.dto.RibDto;
import com.canalplus.technicaltest.model.enumeration.AdresseStatus;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class AdresseSteps {


	private final WireMockServer wireMockServer = new WireMockServer(options().dynamicPort());
	private static final String APPLICATION_JSON = "application/json";
	private final CloseableHttpClient httpClient = HttpClients.createDefault();
	private AbonneDto abonneDto;
	List<ContratDto> contratAbonneList = new ArrayList<>();
	private ObjectMapper objectMapper = new ObjectMapper();
	private ConseillerDto conseillerDto;
	private MouvementDto mouvementdto;

	@Given("^un abonné avec une adresse principale <active> en <pays>$")
	public void getAbonne(String active, String pays) throws Throwable {
		createAbonne(active, pays);
		addContrat(abonneDto);
		assertTrue(active.equals(abonneDto.getAdresse().getStatut().status));
		assertTrue(pays.equals(abonneDto.getAdresse().getPays()));
	}

	@When("^le conseiller connecté à <canal> modifie l'adresse de l'abonné avec ou sans date d'effet$")
	public void modifierAdresseDeContratSelonType(String canal) throws Throwable {
		constructConseillerDto(canal);
		assertTrue(canal.equals(conseillerDto.getCanal()));
		updateAdresse();
		wireMockServer.start();
		configureFor("localhost", wireMockServer.port());
		
		stubFor(put(urlEqualTo("/adresse/update")).withHeader("content-type", equalTo(APPLICATION_JSON))
				.withRequestBody(containing(objectMapper.writeValueAsString(abonneDto.getAdresse())))
				.willReturn(aResponse().withBody(objectMapper.writeValueAsString(abonneDto)).withStatus(200)));
		
		HttpPut request = new HttpPut("http://localhost:" + wireMockServer.port() + "/adresse/update");
		StringEntity entity = new StringEntity(objectMapper.writeValueAsString(abonneDto.getAdresse()));
		request.addHeader("content-type", APPLICATION_JSON);
		request.setEntity(entity);
		HttpResponse response = httpClient.execute(request);
		String responseString = convertResponseToString(response);
		assertThat(responseString, containsString(objectMapper.writeValueAsString(abonneDto.getAdresse())));
		assertEquals(200, response.getStatusLine().getStatusCode());
		wireMockServer.stop();
		
		createMouvement();
	}

	@Then("^l'adresse de l'abonné modifiée est enregistrée sur l'ensemble des contrats de l'abonné$")
	public void then() throws Throwable {
		
		wireMockServer.start();
		configureFor("localhost", wireMockServer.port());
		
		stubFor(get(urlEqualTo("/contrat/list")).withHeader("content-type", equalTo(APPLICATION_JSON))
				.withQueryParam("abonneId", equalTo("" + abonneDto.getId()))
				.willReturn(aResponse().withBody(objectMapper.writeValueAsString(contratAbonneList))));
		
		HttpGet request = new HttpGet("http://localhost:" + wireMockServer.port() + "/contrat/list");
		request.addHeader("content-type", APPLICATION_JSON);
		HttpParams params = request.getParams();
		params.setParameter("abonneId", "" + abonneDto.getId());
		request.setParams(params);
		HttpResponse response = httpClient.execute(request);
		String responseString = convertResponseToString(response);
		assertThat(responseString, containsString(objectMapper.writeValueAsString(contratAbonneList)));
		assertEquals(200, response.getStatusLine().getStatusCode());
		wireMockServer.stop();
	}

	@And("^un mouvement de modification d'adresse est créé$")
	public void and() throws Throwable {
		
		wireMockServer.start();
		configureFor("localhost", wireMockServer.port());
		
		stubFor(get(urlEqualTo("/mouvement/last")).withHeader("content-type", equalTo(APPLICATION_JSON))
				.withQueryParam("abonneId", equalTo("" + abonneDto.getId()))
				.withQueryParam("conseillerId", equalTo("" + conseillerDto.getId()))
				.willReturn(aResponse().withBody(objectMapper.writeValueAsString(mouvementdto))));
		
		HttpGet request = new HttpGet("http://localhost:" + wireMockServer.port() + "/mouvement/last");
		request.addHeader("content-type", APPLICATION_JSON);
		HttpParams params = request.getParams();
		params.setParameter("abonneId", "" + abonneDto.getId());
		params.setParameter("conseillerId", "" + conseillerDto.getId());
		request.setParams(params);
		HttpResponse response = httpClient.execute(request);
		String responseString = convertResponseToString(response);
		assertThat(responseString, containsString(objectMapper.writeValueAsString(mouvementdto)));
		assertEquals(200, response.getStatusLine().getStatusCode());
		wireMockServer.stop();
		
	}

	private void createAbonne(String active, String pays) {
		//CreateAbonne
		RibDto ribDto = new RibDto();
		ribDto.setId(1L);
		ribDto.setBic("SOGEFRPP");
		ribDto.setIban("FR76 00000000000000");
		ribDto.setTitulaire("Aydi Ghada");

		AdresseDto adresseDto = new AdresseDto();
		adresseDto.setStatut(AdresseStatus.ACTIVE.status.equals(active) ? AdresseStatus.ACTIVE : AdresseStatus.INACTIVE);
		adresseDto.setId(1L);
		adresseDto.setNumeroRue(25);
		adresseDto.setNomRue("Rue des fonds ");
		adresseDto.setComplementAdresse("Batiment X");
		adresseDto.setCodePostal(92420);
		adresseDto.setVille("Vaucresson");
		adresseDto.setPays(pays);

		AbonneDto abonneDto = new AbonneDto();
		abonneDto.setId(1L);
		abonneDto.setNom("Aydi");
		abonneDto.setPrenom("Ghada");
		abonneDto.setRib(ribDto);
		abonneDto.setAdresse(adresseDto);
	}
	
	private void addContrat(AbonneDto abonne) {
		ContratDto contratDto = new ContratDto();
		contratDto.setId(1L);
		contratDto.setDateSignature(new Date("11/03/20"));
		contratDto.setDateEffet(new Date("11/04/20"));
		contratDto.setRenouvelable(true);
		contratDto.setAbonne(abonne);
	}

	private void createMouvement() {
		mouvementdto.setId(1L);
		mouvementdto.setAbonneDto(abonneDto);
		mouvementdto.setAction("Update adresse de l'abonne " + abonneDto.getNom());
		mouvementdto.setConseillerDto(conseillerDto);
		mouvementdto.setCreationDate(new Date());
	}
	
	private void updateAdresse() {
		abonneDto.getAdresse().setNomRue("28");
		contratAbonneList.stream().forEach(x -> x.setAbonne(abonneDto));
	}
	
	private void constructConseillerDto(String canal) {
		ConseillerDto conseillerDto = new ConseillerDto();
		conseillerDto.setId(1L);
		conseillerDto.setNom("Nom Conseiller");
		conseillerDto.setPrenom("Prénom cnseiller");
		conseillerDto.setCanal(canal);
	}
	
	private String convertResponseToString(HttpResponse response) throws IOException {
	    InputStream responseStream = response.getEntity().getContent();
	    Scanner scanner = new Scanner(responseStream, "UTF-8");
	    String responseString = scanner.useDelimiter("\\Z").next();
	    scanner.close();
	    return responseString;
	}

}
