
package interpreter.model.adt;

import java.util.HashMap;
import java.util.Map;
import interpreter.exceptions.MyException;
import interpreter.exceptions.ExpressionException;

public class MyDictionary<K,V> implements IDictionary<K,V> {
    private final HashMap<K, V> map;

    public MyDictionary() {
        this.map = new HashMap<>();
    }

    @Override
    public boolean isDefined(K key){
        return map.containsKey(key);
    }

    public void put(K key, V value){
        map.put(key, value);
    }

    public void update(K key, V value){
        if(!(isDefined(key))){
            throw new ExpressionException("unable to find the key!");
        }
        map.put(key, value);
    }

    public V lookup(K key){
        if(!(isDefined(key))){
            throw new ExpressionException("unable to find the key!");
        }
        return map.get(key);
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