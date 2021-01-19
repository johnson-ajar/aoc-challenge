package com.aoc.days.expression;

import java.awt.Dimension;
import java.io.StringWriter;
import java.io.Writer;
import java.net.URI;
import java.net.URISyntaxException;
import java.rmi.server.ExportException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import org.jgrapht.Graph;
import org.jgrapht.ext.JGraphXAdapter;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.nio.Attribute;
import org.jgrapht.nio.DefaultAttribute;
import org.jgrapht.nio.dot.DOTExporter;

import com.mxgraph.layout.mxFastOrganicLayout;
import com.mxgraph.layout.mxIGraphLayout;
import com.mxgraph.swing.mxGraphComponent;

public class ExpressionTreeVisualizer {
	public ExpressionTreeVisualizer() {
		
	}
	
	public static void main(String[] args) throws URISyntaxException, ExportException {
		Graph<String, DefaultEdge> stringGraph = createStringGraph();
		System.out.println(stringGraph.toString());
		System.out.println();
		
		visualize(stringGraph);
		
		// create a graph based on URI objects
        Graph<URI, DefaultEdge> hrefGraph = createHrefGraph();

		System.out.println("-- renderHrefGraph output");
        renderHrefGraph(hrefGraph);
        System.out.println();
	}
	
	 /**
     * Create a toy graph based on String objects.
     *
     * @return a graph based on String objects.
     */
    private static Graph<String, DefaultEdge> createStringGraph()
    {
        Graph<String, DefaultEdge> g = new SimpleGraph<>(DefaultEdge.class);

        String v1 = "v1";
        String v2 = "v2";
        String v3 = "v3";
        String v4 = "v4";

        // add the vertices
        g.addVertex(v1);
        g.addVertex(v2);
        g.addVertex(v3);
        g.addVertex(v4);

        // add edges to create a circuit
        g.addEdge(v1, v2);
        g.addEdge(v2, v3);
        g.addEdge(v3, v4);
        g.addEdge(v4, v1);
        
        return g;
    }
    
    private static Graph<URI, DefaultEdge> createHrefGraph()
            throws URISyntaxException
        {

            Graph<URI, DefaultEdge> g = new DefaultDirectedGraph<>(DefaultEdge.class);

            URI google = new URI("http://www.google.com");
            URI wikipedia = new URI("http://www.wikipedia.org");
            URI jgrapht = new URI("http://www.jgrapht.org");

            // add the vertices
            g.addVertex(google);
            g.addVertex(wikipedia);
            g.addVertex(jgrapht);

            // add edges to create linking structure
            g.addEdge(jgrapht, wikipedia);
            g.addEdge(google, jgrapht);
            g.addEdge(google, wikipedia);
            g.addEdge(wikipedia, google);


            return g;
        }
    /**
     * Render a graph in DOT format.
     *
     * @param hrefGraph a graph based on URI objects
     */
    private static void renderHrefGraph(Graph<URI, DefaultEdge> hrefGraph)
        throws ExportException
    {

        DOTExporter<URI, DefaultEdge> exporter =
            new DOTExporter<>(v -> v.getHost().replace('.', '_'));
        exporter.setVertexAttributeProvider((v) -> {
            Map<String, Attribute> map = new LinkedHashMap<>();
            map.put("label", DefaultAttribute.createAttribute(v.toString()));
            return map;
        });
        Writer writer = new StringWriter();
        exporter.exportGraph(hrefGraph, writer);
        System.out.println(writer.toString());
    }
    
    public static JFrame visualize(Graph<String, DefaultEdge> graph) {
        // show network mapGraph
        if (graph.vertexSet().size() > 0) {
            JGraphXAdapter<String, DefaultEdge> jGraphXAdapter = new JGraphXAdapter<>(graph);

            jGraphXAdapter.getModel().beginUpdate();

            mxIGraphLayout layout = new mxFastOrganicLayout(jGraphXAdapter);
            layout.execute(jGraphXAdapter.getDefaultParent());

            jGraphXAdapter.getModel().endUpdate();

            mxGraphComponent graphComponent = new mxGraphComponent(jGraphXAdapter);

            JFrame frame = new JFrame();
            frame.setTitle(String.format("%s(V=%d,E=%d)", "Viewing graph", graph.vertexSet().size(), graph.edgeSet().size()));
            frame.setSize(new Dimension(1200, 1600));
            frame.getContentPane().add(new JScrollPane(graphComponent));
            frame.pack();
            frame.setVisible(true);
            
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            return frame;
        }
        return null;
    }
}
