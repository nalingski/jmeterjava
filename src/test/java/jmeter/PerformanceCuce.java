package jmeter;

/**
 * Created by goonawardanan on 21/03/2016.
 */

import org.apache.jmeter.control.LoopController;
import org.apache.jmeter.engine.StandardJMeterEngine;
import org.apache.jmeter.protocol.http.sampler.HTTPSampler;
import org.apache.jmeter.testelement.TestElement;
import org.apache.jmeter.testelement.TestPlan;
import org.apache.jmeter.threads.SetupThreadGroup;
import org.apache.jmeter.threads.ThreadGroup;
import org.apache.jmeter.util.JMeterUtils;
import org.apache.jorphan.collections.HashTree;

public class PerformanceCuce {

    public static void main(String[] argv) throws Exception {

        attempt2();

    }

    public static void attempt1() {
        //JMeter Engine
        StandardJMeterEngine jmeter = new StandardJMeterEngine();

        //JMeter initialization (properties, log levels, locale, etc)
        JMeterUtils.loadJMeterProperties("C:\\Users\\goonawardanan\\Downloads\\sw\\apache-jmeter-2.13\\bin\\jmeter.properties");
        JMeterUtils.setJMeterHome("C:\\Users\\goonawardanan\\Downloads\\sw\\apache-jmeter-2.13");
        JMeterUtils.initLogging();// you can comment this line out to see extra log messages of i.e. DEBUG level
        JMeterUtils.initLocale();

        // JMeter Test Plan, basic all u JOrphan HashTree
        HashTree testPlanTree = new HashTree();

        // HTTP Sampler
        HTTPSampler httpSampler = new HTTPSampler();
        httpSampler.setDomain("127.0.0.1");
        httpSampler.setPort(8000);
        httpSampler.setPath("/");
        httpSampler.setMethod("GET");

        // Loop Controller
        LoopController loopController = new LoopController();
        loopController.setLoops(1);
        loopController.addTestElement(httpSampler);
        loopController.setFirst(true);
        loopController.initialize();

        // Thread Group
        ThreadGroup threadGroup = new ThreadGroup();
        threadGroup.setNumThreads(1);
        threadGroup.setRampUp(1);
        threadGroup.setSamplerController(loopController);

        // Test Plan
        TestPlan testPlan = new TestPlan("Create JMeter Script From Java Code");

        // Construct Test Plan from previously initialized elements
        testPlanTree.add("testPlan", testPlan);
        testPlanTree.add("loopController", loopController);
        testPlanTree.add("threadGroup", threadGroup);
        testPlanTree.add("httpSampler", httpSampler);

        // Run Test Plan
        jmeter.configure(testPlanTree);
        jmeter.run();
    }

    public static void attempt2() {
        // Engine
        StandardJMeterEngine jm = new StandardJMeterEngine();
        // jmeter.properties
        JMeterUtils.loadJMeterProperties("C:\\Users\\goonawardanan\\Downloads\\sw\\apache-jmeter-2.13\\bin\\jmeter.properties");

        HashTree hashTree = new HashTree();

        // HTTP Sampler
        HTTPSampler httpSampler = new HTTPSampler();
        httpSampler.setDomain("127.0.0.1");
        httpSampler.setPort(8000);
        httpSampler.setPath("/");
        httpSampler.setMethod("GET");

        // Loop Controller
        TestElement loopCtrl = new LoopController();
        ((LoopController) loopCtrl).setLoops(1);
        ((LoopController) loopCtrl).addTestElement(httpSampler);
        ((LoopController) loopCtrl).setFirst(true);

        // Thread Group
        SetupThreadGroup threadGroup = new SetupThreadGroup();
        threadGroup.setNumThreads(1);
        threadGroup.setRampUp(1);
        threadGroup.setSamplerController((LoopController) loopCtrl);

        // Test plan
        TestPlan testPlan = new TestPlan("MY TEST PLAN");

        hashTree.add("testPlan", testPlan);
        hashTree.add("loopCtrl", loopCtrl);
        hashTree.add("threadGroup", threadGroup);
        hashTree.add("httpSampler", httpSampler);

        jm.configure(hashTree);

        jm.run();


    }
}