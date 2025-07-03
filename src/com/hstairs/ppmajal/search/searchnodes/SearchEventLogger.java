package com.hstairs.ppmajal.search.searchnodes;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import com.hstairs.ppmajal.search.searchnodes.PosthocClient;

public class SearchEventLogger {

    private JSONArray events;
    public PosthocClient wsClient; 

    public SearchEventLogger() {
        events = new JSONArray();
        wsClient = null;
    }
    
    private void sendEventWS(JSONObject event) {
        if (wsClient != null && wsClient.isOpen()) {
            System.out.println(event.toString());
            wsClient.send(event.toString());
        }
    }

    public void logExpand(SearchNode node) {
        JSONObject event = new JSONObject();
        event.put("type", "expand");
        event.put("id", node.getId());
        event.put("pId", node.getParentId());
        event.put("f", node.getF());
        event.put("g", node.getG());
        event.put("heuristicValue", node.getHValue());

        Object state = node.getJsonState();
        event.put("state", state);

        //event.put("diff", new JSONObject());

        events.put(event);
        sendEventWS(event);
    }

    public void logExpandIda(IdaStarSearchNode node) {
        JSONObject event = new JSONObject();
        event.put("type", "expand");
        event.put("id", node.getId());
        event.put("pId", node.getParentId());
        event.put("g", node.getG());

        Object state = node.getJsonState();
        event.put("state", state);

        //event.put("diff", new JSONObject());

        events.put(event);
        sendEventWS(event);
    }

    public void logGenerate(SearchNode node, SearchNode parent) {
        JSONObject event = new JSONObject();
        event.put("type", "generate");
        event.put("id", node.getId());
        event.put("pId", node.getParentId());
        event.put("f", node.getF());
        event.put("g", node.getG());
        event.put("heuristicValue", node.getHValue());
        if (node.getParentId() == null) {
            event.put("action", "init_state");
        } else if (node.getTransition() == null) {
            event.put("list_of_actions", "waiting");
        } else {
            event.put("action", node.getTransition().toString());
        }

        Object state = node.getJsonState();
        event.put("state", state);

        String childStateStr = node.getState();
        String parentStateStr = parent != null ? parent.getState() : "";
        JSONObject diff = computeDiff(parentStateStr, childStateStr);

        event.put("diff", diff);

        events.put(event);
        sendEventWS(event);
    }

    public void logGenerateIda(IdaStarSearchNode node, IdaStarSearchNode parent) {
        JSONObject event = new JSONObject();
        event.put("type", "generate");
        event.put("id", node.getId());
        event.put("pId", node.getParentId());
        event.put("g", node.getG());
        if (node.getParentId() == null) {
            event.put("action", "init_state");
        } else if (node.getTransition() == null) {
            event.put("list_of_actions", "waiting");
        } else {
            event.put("action", node.getTransition().toString());
        }

        Object state = node.getJsonState();
        event.put("state", state);

        String childStateStr = node.getState();
        String parentStateStr = parent != null ? parent.getState() : "";
        JSONObject diff = computeDiff(parentStateStr, childStateStr);

        event.put("diff", diff);

        events.put(event);
        sendEventWS(event);
    }

    public void logClose(SearchNode node) {
        JSONObject event = new JSONObject();
        event.put("type", "close");
        event.put("id", node.getId());
        event.put("pId", node.getParentId());
        event.put("f", node.getF());
        event.put("g", node.getG());
        event.put("heuristicValue", node.getHValue());


        Object state = node.getJsonState();
        event.put("state", state);

        //event.put("diff", new JSONObject());

        events.put(event);
        sendEventWS(event);
    }

    public void logCloseIda(IdaStarSearchNode node) {
        JSONObject event = new JSONObject();
        event.put("type", "close");
        event.put("id", node.getId());
        event.put("pId", node.getParentId());
        event.put("g", node.getG());

        Object state = node.getJsonState();
        event.put("state", state);

        //event.put("diff", new JSONObject());

        events.put(event);
        sendEventWS(event);
    }

    public void writeEventsToFile(String filename) {
        JSONObject root = new JSONObject();
        root.put("events", events);
        System.out.println("Successfully Copied JSON Events to File...");
        try (FileWriter file = new FileWriter(filename)) {
            file.write(root.toString(2)); 
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Map<String, String> extractStateMap(String stateStr) {
        Map<String, String> stateMap = new HashMap<>();
        String[] lines = stateStr.split("\\n");
        for (String line : lines) {
            String[] atoms = line.trim().split("\\s+");
            for (String atom : atoms) {
                atom = atom.trim();
                if (atom.isEmpty()) continue;
                if (atom.contains("=")) {
                    int idx = atom.indexOf(")=");
                    if (idx > 0) {
                        String key = atom.substring(0, idx+1); 
                        String value = atom.substring(idx+2);
                        stateMap.put(key, value);
                    }
                } else if (atom.contains(")=true") || atom.contains(")=false")) {
                    int idx = atom.indexOf(")=");
                    if (idx > 0) {
                        String key = atom.substring(0, idx+1);
                        String value = atom.substring(idx+2);
                        stateMap.put(key, value);
                    }
                } else if (atom.startsWith("(") && atom.endsWith(")")) {
                    stateMap.put(atom, "true");
                }
            }
        }
        return stateMap;
    }

    // Compute diff between 2 states (father and son)
    private JSONObject computeDiff(String parentStateStr, String childStateStr) {
        Map<String, String> parent = extractStateMap(parentStateStr);
        Map<String, String> child = extractStateMap(childStateStr);
        JSONObject diff = new JSONObject();

        for (String key : child.keySet()) {
            String childVal = child.get(key);
            String parentVal = parent.get(key);
            if (parentVal == null || !parentVal.equals(childVal)) {
                JSONObject change = new JSONObject();
                change.put("from", parentVal);
                change.put("to", childVal);
                diff.put(key, change);
            }
        }
        for (String key : parent.keySet()) {
            if (!child.containsKey(key)) {
                JSONObject change = new JSONObject();
                change.put("from", parent.get(key));
                change.put("to", JSONObject.NULL);
                diff.put(key, change);
            }
        }
        return diff;
    }

    public void setWsClient(PosthocClient wsClient) {
        this.wsClient = wsClient;
    }
}
