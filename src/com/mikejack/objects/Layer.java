package com.mikejack.objects;

import java.util.ArrayList;

import com.mikejack.engine.GameContainer;

public class Layer {

    private ArrayList<GameObject> gameObjects;
    
    public Layer() {
	gameObjects = new ArrayList<GameObject>();
    }
    
    public void update(GameContainer gc) {
	for (GameObject object : gameObjects) {
	    object.update(gc);
	}
    }
    
    public void render(GameContainer gc) {
	for (GameObject object : gameObjects) {
	    object.render(gc);
	}
    }
    
    public void addObject(GameObject object) {
	gameObjects.add(object);
    }
    
    public void removeObject(GameObject object) {
	gameObjects.remove(object);
    }

    public ArrayList<GameObject> getGameObjects() {
        return gameObjects;
    }
    
}
