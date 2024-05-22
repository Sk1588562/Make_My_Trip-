package in.ashokeit.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import in.ashokeit.request.Passenger;
import in.ashokeit.response.Ticket;


@Service
public class MakeMyTripServiceImpl implements MakeMyTrip {
	
	private String BOOK_TICKET_URL="localhost:8080/ticket";
	
	private String GET_TICKET_URL="localhost:8080/ticket/{ticketNum}";

	@Override
	public Ticket bookTicket(Passenger passenger) {
		
		//get the instance of webclient (impl class)
		WebClient webClient = WebClient.create();
				
		//send POST request with passenger data
		//and map response to Ticket Obj
		
		Ticket ticket = webClient.post()
		                         .uri(BOOK_TICKET_URL)
		                         .bodyValue(passenger)
		                         .retrieve()
		                         .bodyToMono(Ticket.class)
		                         .block();
		
		return ticket;
		
		/*RestTemplate rt = new RestTemplate();
		ResponseEntity<Ticket> responseEntity = rt.postForEntity(BOOK_TICKET_URL, passenger, Ticket.class);
		Ticket ticket = responseEntity.getBody();
		return ticket;*/
	}

	@Override
	public Ticket getTicketByNum(Integer ticketNumber) {
		
		//get the instance of webclient (impl class)
		WebClient webClient = WebClient.create();
		
		//send the request and map response to Ticket Obj
		
		
		 Ticket ticket = webClient.get()
		                          .uri(GET_TICKET_URL, ticketNumber)
		                          .retrieve()
		                          .bodyToMono(Ticket.class)
		                          .block(); // sync call
		
		 return ticket;
		
		/*RestTemplate rt = new RestTemplate();
		ResponseEntity<Ticket> responseEntity = rt.getForEntity(GET_TICKET_URL, Ticket.class, ticketNumber);
		Ticket ticket = responseEntity.getBody();	
		return ticket*/
		
	}

}
