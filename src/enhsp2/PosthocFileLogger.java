package enhsp2;

import com.hstairs.ppmajal.extraUtils.ExternalLoggerLogType;
import com.hstairs.ppmajal.extraUtils.IExternalLogger;
import com.hstairs.ppmajal.search.searchnodes.IdaStarSearchNode;
import com.hstairs.ppmajal.search.searchnodes.SearchNode;
import com.hstairs.ppmajal.search.searchnodes.SimpleSearchNode;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class PosthocFileLogger implements IExternalLogger {
    private String filePath;
    // Store all the generated events to save in bulk
    private List<JSONObject> events;

    public PosthocFileLogger(String filePath) {
        this.filePath = filePath;
        this.events = new ArrayList<>();
    }

    @Override
    public void log(SimpleSearchNode node, ExternalLoggerLogType logType) {
        String eventType = logType.toString();

        JSONObject event = new JSONObject();
        event.put("id", node.id.toString());
        event.put("g", node.gValue);
        if(node.father != null) {
            event.put("pId", node.father.id.toString());
        }
        else{
            event.put("pId", null);
        }
        event.put("type", eventType);

        if(node instanceof SearchNode) {
            SearchNode searchNode = (SearchNode)node;

            // Merge the saved jsonRepresentation with the one created
            JSONObject savedData = searchNode.jsonRepresentation;
            if(savedData != null) {
                for(Object key : savedData.keySet()) {
                    event.put(key, savedData.get(key));
                }
            }
            event.put("f", searchNode.f);

        }
        
        this.events.add(event);
    }

    @Override 
    public void beforeExecution() {
        File file = new File(filePath);
        if(file.exists()) {
            file.delete();
        }
    }
    
    @Override
    public void afterExecution() {
        writeSavedEvents();
    }

    private void writeSavedEvents() {
        File file = new File(this.filePath);
        JSONObject root;
        JSONArray existingEvents;

        try {
            if(file.exists()) {
                String content = new String(Files.readAllBytes(Paths.get(filePath)));
                JSONParser parser = new JSONParser();
                root = (JSONObject) parser.parse(content);
                
                existingEvents = (JSONArray) root.get("events");
                if(existingEvents == null) {
                    existingEvents = new JSONArray();
                }

            } else {
                root = new JSONObject();
                root.put("version", "1.4.0");
                existingEvents = new JSONArray();
            }

            existingEvents.addAll(this.events);
            root.put("events", existingEvents);

            try (FileWriter writer = new FileWriter(filePath)) {
                writer.write(root.toJSONString());
            }

            this.events = new ArrayList<>();
        } catch(IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}
