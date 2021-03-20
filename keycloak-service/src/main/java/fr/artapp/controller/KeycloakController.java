package fr.artapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.artapp.model.UserCredentials;
import fr.artapp.model.UserDTO;
import fr.artapp.services.KeyCloakService;

@RestController
@RequestMapping(value = "/keycloak")
public class KeycloakController {

	@Autowired
	KeyCloakService keyClockService;


	@RequestMapping(value = "/token", method = RequestMethod.POST)
	public ResponseEntity<?> getTokenUsingCredentials(@RequestBody UserCredentials userCredentials) {

		String responseToken = null;
		try {

			responseToken = keyClockService.getToken(userCredentials);

		} catch (Exception e) {

			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(responseToken, HttpStatus.OK);

	}


	@RequestMapping(value = "/refreshtoken", method = RequestMethod.GET)
	public ResponseEntity<?> getTokenUsingRefreshToken(@RequestHeader(value = "Authorization") String refreshToken) {

		String responseToken = null;
		try {

			responseToken = keyClockService.getByRefreshToken(refreshToken);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(responseToken, HttpStatus.OK);

	}


	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ResponseEntity<?> createUser(@RequestBody UserDTO userDTO) {
		int status=0;
		try {

			 status =keyClockService.createUserInKeyCloak(userDTO);
			return ResponseEntity.ok(status);
		}

		catch (Exception ex) {

			//ex.printStackTrace();
			return  ResponseEntity.badRequest().body("not created "+status);

		}

	}
}
