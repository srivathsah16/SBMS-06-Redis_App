package com.srivath.rest;

import java.util.Collection;
import java.util.Map;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.srivath.databinding.Country;

@RestController
public class CountryController {
	
	HashOperations<String, Object, Object> opsForHash;

	// @Autowired annotation is optional here since this is the only one constructor defined in this
	// class.
	public CountryController(RedisTemplate<String, Country> redisTemplate) {
		this.opsForHash = redisTemplate.opsForHash();
	}

	@PostMapping("/country") 
	public String addCountry(@RequestBody Country country) {
		opsForHash.put("COUNTRY", country.getSno(), country);
		return "country added.";
	}

	@GetMapping("/countries")
	public Collection<Object> getCountries() {
		Map<Object, Object> map = opsForHash.entries("COUNTRY");
		return map.values();
	}

	@GetMapping("/getCountry/{id}") 
	public Country getCountryById(@PathVariable("id") Integer countryId) {
		Country country = (Country) opsForHash.get("COUNTRY", countryId);
		return country;
	}
}
