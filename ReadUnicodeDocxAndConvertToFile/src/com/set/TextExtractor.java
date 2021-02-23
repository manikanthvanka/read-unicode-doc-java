package com.set;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.tika.detect.DefaultDetector;
import org.apache.tika.detect.Detector;
import org.apache.tika.io.TikaInputStream;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.ContentHandler;

public class TextExtractor { 
    private OutputStream outputstream;
    private ParseContext context;
    private Detector detector;
    private Parser parser;
    private Metadata metadata;
    private String extractedText;

    public TextExtractor() {
        context = new ParseContext();
        detector = new DefaultDetector();
        parser = new AutoDetectParser(detector);
        context.set(Parser.class, parser);
        outputstream = new ByteArrayOutputStream();
        metadata = new Metadata();
    }

    public void process(String filename) throws Exception {
        URL url;
        File file = new File(filename);
        if (file.isFile()) {
            url = file.toURI().toURL();
        } else {
            url = new URL(filename);
        }
        InputStream input = TikaInputStream.get(url, metadata);
        ContentHandler handler = new BodyContentHandler(outputstream);
        parser.parse(input, handler, metadata, context); 
        input.close();
    }

    public void getString() throws IOException {
        //Get the text into a String object
        extractedText = outputstream.toString();
        String content = extractedText;
        
        Files.write(Paths.get("/Users/manikanthvanka/Downloads/output.txt"), content.getBytes());
        
        System.out.println("Successfully Written to Text file");
    }

    public static void main(String args[]) throws Exception {
    	try {

            TextExtractor textExtractor = new TextExtractor();
            textExtractor.process("/Users/manikanthvanka/Downloads/కథలు.docx");
            textExtractor.getString();
            
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    }
    
   
    
    
}
