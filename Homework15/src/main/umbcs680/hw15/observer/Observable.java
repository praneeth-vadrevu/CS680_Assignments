package umbcs680.hw15.observer;

import java.util.LinkedList;
import java.util.List;

public abstract class Observable<T> {
    private LinkedList<Observer<T>> observers = new LinkedList<>();

    public void addObserver(Observer<T> o) {
        observers.add(o);
    }

    public void clearObservers() {
        observers.clear();

    }
    public List<Observer<T>> getObservers(){
        return observers;
    }

    public int countObservers() {
        return observers.size();

    }
    public void removeObserver(Observer<T> o) {
        observers.remove(o);
    }

    public void notifyObservers(T event) {
        for(Observer<T> ob: observers) {
            ob.update(this, event);
        }
//		observers.forEach( (observer)->{observer.update(this, event);} );
    }

}
