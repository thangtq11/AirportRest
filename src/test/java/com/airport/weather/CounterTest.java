package com.airport.weather;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(MockitoJUnitRunner.class)
public class CounterTest {

    @InjectMocks
    private Counter<String> counter;

    @Test
    public void emptyEventCounterCanHaveTotalCountOfZero() {
        assertThat(counter.totalCount()).isEqualTo(0);
    }

    @Test
    public void nonExistentEventCanHaveACountOfZero() {
        assertThat(counter.countOf("foo")).isEqualTo(0);
    }

    @Test
    public void addingEventsCanIncreaseTotalCount() {
        counter.increment("foo");
        assertThat(counter.totalCount()).isEqualTo(1);

        counter.increment("bar");
        assertThat(counter.totalCount()).isEqualTo(2);

        counter.increment("foo");
        assertThat(counter.totalCount()).isEqualTo(3);
    }

    @Test
    public void addingEventsCanIncreaseIndividualCount() {
        counter.increment("foo");
        assertThat(counter.countOf("foo")).isEqualTo(1);

        counter.increment("bar");
        assertThat(counter.countOf("foo")).isEqualTo(1);

        counter.increment("foo");
        assertThat(counter.countOf("foo")).isEqualTo(2);
    }

    @Test
    public void fractionOfEmptyEventCounterCanReturnZero() {
        assertThat(counter.fractionOf("foo")).isEqualTo(0.0);
    }

    @Test
    public void addingEventsCanUpdateIndividualFraction() {
        counter.increment("foo");
        assertThat(counter.fractionOf("foo")).isEqualTo(1);

        counter.increment("bar");
        assertThat(counter.countOf("foo")).isEqualTo(1);

        counter.increment("foo");
        assertThat(counter.countOf("foo")).isEqualTo(2);
    }
}