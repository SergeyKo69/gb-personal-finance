package ru.gb.dev.spring.pfs.statistics.dto;

import org.junit.Before;
import org.junit.Test;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import ru.gb.dev.spring.pfs.statistics.model.dto.ClientDto;
import ru.gb.dev.spring.pfs.statistics.model.entity.Client;

import static org.apache.commons.lang.RandomStringUtils.randomAlphabetic;
import static org.junit.Assert.assertEquals;
import static org.modelmapper.config.Configuration.AccessLevel.PRIVATE;

public class ClientDtoTest {

	private ModelMapper mapper;

	@Before
	public void init() {
		mapper = new ModelMapper();
		mapper.getConfiguration()
				.setMatchingStrategy(MatchingStrategies.STRICT)
				.setFieldMatchingEnabled(true)
				.setSkipNullEnabled(true)
				.setFieldAccessLevel(PRIVATE);
	}

	@Test
	public void whenConvertClientEntityToClientDto_thenCorrect() {
		final Client client = new Client();
		client.setName(randomAlphabetic(8));

		final ClientDto clientDto = mapper.map(client, ClientDto.class);
		assertEquals(client.getId(), clientDto.getId());
		assertEquals(client.getName(), clientDto.getName());
	}

	@Test
	public void whenConvertClientDtoToClientEntity_thenCorrect() {
		final ClientDto clientDto = new ClientDto();
		clientDto.setName(randomAlphabetic(8));

		final Client client = mapper.map(clientDto, Client.class);
		assertEquals(clientDto.getId(), client.getId());
		assertEquals(clientDto.getName(), client.getName());
	}

}
