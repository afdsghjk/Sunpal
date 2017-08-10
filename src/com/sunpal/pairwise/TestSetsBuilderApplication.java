package com.sunpal.pairwise;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestSetsBuilderApplication {
	
	private static final String NAV_SCENARIO =
	        "Browser: Chrome, Firefox, InternetExplorer, Safari" 
	      + "\nPage: Home, Category, Search, New Products"
	      + "\nProduct: Phone, Movie, Computer, Blender, Microwave, Book, Sweater"
	      + "\nClick: Link, Image, Description"
	      ;

	public static void buildTestSets() {
		Logger log = LoggerFactory.getLogger(TestSetsBuilderApplication.class);
		
	    //First, generate the list of vectors we *want*
	    IInventory inventory = PairwiseInventoryFactory.generateParameterInventory(NAV_SCENARIO);
	    List<Map<String, String>> rawDataSet = inventory.getTestDataSet().getTestSets();

	    //Now, go through the vectors in the database to figure out what we already *have*
	    // If we don't have it already, create it
	    for (Map<String, String> rawTestCase: rawDataSet) {
	        log.debug(String.format("Looking for Vector: [%s] [%s] [%s] [%s]", 
	            rawTestCase.get("Browser"), rawTestCase.get("Page"), 
	            rawTestCase.get("Product"), rawTestCase.get("Click")));
	        //Now do something with it...
	    }
	}
	
	public static void main(String[] args) {
		buildTestSets();
	}

}
