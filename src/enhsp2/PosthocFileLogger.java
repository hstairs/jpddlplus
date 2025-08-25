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
            event.put("parentId", node.father.id.toString());
        }
        event.put("type", eventType);

        if(node instanceof SearchNode) {
            SearchNode searchNode = (SearchNode)node;

            // Merge the saved jsonRepresentation with the one created
            JSONObject savedData = searchNode.jsonRepresentation;
            Iterator savedDataKeys = savedData.keys();
            while(savedDataKeys.hasNext()) {
                String key = (String)savedDataKeys.next();
                event.put(key, savedData.get(key));
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
                root = new JSONObject(content);
                existingEvents = root.optJSONArray("events");
                if(existingEvents == null) {
                    existingEvents = new JSONArray();
                }

            } else {
                root = new JSONObject();
                root.put("version", "1.0.5");
                existingEvents = new JSONArray();
            }

            for(JSONObject event : this.events) {
                existingEvents.put(event);
            }
            root.put("events", existingEvents);

            try (FileWriter writer = new FileWriter(filePath)) {
                writer.write(root.toString(2));
            }

            this.events = new ArrayList<>();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
