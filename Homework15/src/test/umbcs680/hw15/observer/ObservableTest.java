package umbcs680.hw15.observer;

import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ObservableTest {

    // Minimal concrete implementation to instantiate Observable
    private static class TestObservable extends Observable<String> {}

    @Test
    public void testAddObserver() {
        TestObservable observable = new TestObservable();
        Observer<String> observer = (sender, event) -> {};
        observable.addObserver(observer);
        List<Observer<String>> observers = observable.getObservers();
        assertEquals(1, observers.size());
        assertTrue(observers.contains(observer));
    }

    @Test
    public void testRemoveObserver() {
        TestObservable observable = new TestObservable();
        Observer<String> observer = (sender, event) -> {};
        observable.addObserver(observer);
        observable.removeObserver(observer);
        List<Observer<String>> observers = observable.getObservers();
        assertEquals(0, observers.size());
    }

    @Test
    public void testClearObservers() {
        TestObservable observable = new TestObservable();
        observable.addObserver((sender, event) -> {});
        observable.addObserver((sender, event) -> {});
        observable.clearObservers();
        assertEquals(0, observable.countObservers());
    }

    @Test
    public void testCountObservers() {
        TestObservable observable = new TestObservable();
        assertEquals(0, observable.countObservers());
        observable.addObserver((sender, event) -> {});
        assertEquals(1, observable.countObservers());
    }

    @Test
    public void testGetObservers() {
        TestObservable observable = new TestObservable();
        Observer<String> observer = (sender, event) -> {};
        observable.addObserver(observer);
        List<Observer<String>> observers = observable.getObservers();
        assertEquals(1, observers.size());
        assertSame(observer, observers.get(0));
    }
}
