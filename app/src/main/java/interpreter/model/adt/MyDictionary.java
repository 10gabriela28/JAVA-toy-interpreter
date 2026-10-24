
package interpreter.model.adt;

import java.util.HashMap;
import java.util.Map;
import interpreter.exceptions.MyException;
import interpreter.exceptions.ExpressionException;
import interpreter.exceptions.ADTException;

public class MyDictionary<K,V> implements IDictionary<K,V> {
    private final HashMap<K, V> map;

    public MyDictionary() {
        this.map = new HashMap<>();
    }

    @Override
    public boolean isDefined(K key){
        return map.containsKey(key);
    }

    @Override
    public void put(K key, V value){
        map.put(key, value);
    }

    @Override
    public void update(K key, V value) throws MyException {
        if(!(isDefined(key))){
            throw new ADTException("unable to find the key!");
        }
        map.put(key, value);
    }

    @Override
    public V lookup(K key) throws MyException {
        if(!(isDefined(key))){
            throw new ADTException("unable to find the key!");
        }
        return map.get(key);
    }

    @Override
    public void remove(K key) throws MyException {
        if(!(isDefined(key))){
            throw new ADTException("unable to find the key!");
        }
        map.remove(key);
    }

    @Override
    public Map<K, V> getContent() {
        return map;
    }

    @Override
    public String toString(){
        return map.toString();
    }
}

// a dictionary is a collection of key-value pairs.
// each key is unique and maps to exactly one value.

// what to know about a dictionary:
// - dynamic size
// - direct access