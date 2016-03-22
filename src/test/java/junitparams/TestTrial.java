package junitparams;

import junitparams.providers.PersonProvider;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by goonawardanan on 21/03/2016.
 */

@RunWith(JUnitParamsRunner.class)
public class TestTrial {

    @Test
    @Parameters
    public void personIsAdult(Person person, boolean valid) throws Exception {
        assertThat(person.isAdult(), is(valid));
    }

    private Object[] parametersForPersonIsAdult() {
        return new Object[]{
                new Object[]{new Person(13), false},
                new Object[]{new Person(17), false},
                new Object[]{new Person(18), true},
                new Object[]{new Person(22), true}
        };
    }


    @Test
    @Parameters(source = PersonProvider.class)
    public void personIsAdultFromAProvider(Person person, boolean valid) throws Exception {
        assertThat(person.isAdult(), is(valid));
    }


}
