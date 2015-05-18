
package ro.uvt.observer;

import java.util.HashMap;

public interface Subject {

  public void registerObserver(Observer toRegister);

  public void removeObserver(Observer toRemove);

  public void notifyObservers();
  
  public HashMap<String, Object> getState();
}