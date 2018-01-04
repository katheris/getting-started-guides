package hello;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cloudant.client.api.Database;
import com.cloudant.client.api.model.Response;

@RestController
@RequestMapping("/greeting")
public class GreetingController {

    @Autowired
    private Database db;

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody List<Greeting> getAll() throws IOException {
        List<Greeting> allDocs = db.getAllDocsRequestBuilder().includeDocs(true).build().getResponse().getDocsAs(Greeting.class);
        return allDocs;
    }

    @RequestMapping(method = RequestMethod.GET, value="/{id}")
    public @ResponseBody Greeting getGreeting(@PathVariable String id) throws IOException {
        Greeting greeting = db.find(Greeting.class, id);
        return greeting;
    }

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody String add(@RequestBody Greeting greeting) {
        Response response = db.post(greeting);
        String id = response.getId();
        return id;
    }

    @RequestMapping(method=RequestMethod.DELETE, value="/{id}")
    public ResponseEntity<?> deleteGreeting(@PathVariable String id) throws IOException {
        Greeting greeting = db.find(Greeting.class, id);
        Response remove = db.remove(greeting.get_id(),greeting.get_rev());
        return new ResponseEntity<String>(remove.getReason(), HttpStatus.valueOf(remove.getStatusCode()));
    }
}
