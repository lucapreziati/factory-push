package it.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import it.test.model.Base;
import it.test.model.Reference;

@RestController
@RequestMapping("/reference")

public class ReferenceController {

	RestTemplate rt = new RestTemplate();

	@GetMapping
	@ResponseBody
	public Base getBase() {
		Base b = new Base();

		b.setUser(createReference(0));

		List<Reference> list = new ArrayList<>();

		for (int i = 0; i < 5; i++) {
			list.add(createReference(i));
		}
		for(int i =0; i < 5; i++) {
			list.add(list.get(i));
		}
		for(int i =0; i < 5; i++) {
			list.add(list.get(i));
		}
		
		b.setFriends(list);

		
		return b;

	}

	@GetMapping(path = "/clone")
	@ResponseBody
	public Base getCloneBase() {
		ResponseEntity<Base> res = rt.getForEntity("http://localhost:8080/reference", Base.class);

		if (res.getStatusCodeValue() == 200) {
			Base b = res.getBody();

			System.out.println(b);
			return b;
		}

		return null;

	}

	public Reference createReference(int i) {
		Reference r = new Reference();
		r.setBase("" + i);
		r.setCod("" + i);
		r.setSuperCod("" + i);
		r.setBase(generateString());

		return r;
	}

	public String generateString() {
		int leftLimit = 97; // letter 'a'
		int rightLimit = 122; // letter 'z'
		int targetStringLength = 10;
		Random random = new Random();

		return random.ints(leftLimit, rightLimit + 1).limit(targetStringLength)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();

	}
}
