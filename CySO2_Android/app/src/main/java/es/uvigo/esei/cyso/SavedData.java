package es.uvigo.esei.cyso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xoelnovoaperez on 6/6/17.
 */

class SavedData implements Serializable {

    private String dni;
    private String[] cons, repo;
    private Map<String, String> idList;

    public static SavedData data;

    static SavedData getInstance(String[] cons, String[] repo, String dni) {
        if(data == null)
            data = new SavedData(cons, repo, dni);
        return data;
    }

    private SavedData(String[] cons, String[] repo, String dni) {
        this.cons = cons;
        this.repo = repo;
        this.dni = dni;
        this.idList = new HashMap<>();
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String[] getCons() {
        return cons;
    }

    public void setCons(String[] cons) {
        this.cons = cons;
    }

    public String[] getRepo() {
        return repo;
    }

    public void setRepo(String[] repo) {
        this.repo = repo;
    }

    public static SavedData getData() {
        return data;
    }

    public static void setData(SavedData data) {
        SavedData.data = data;
    }

    public Map<String, String> getIdList() {
        return idList;
    }

    public void addItemIdList(String key, String id) {
        this.idList.put(key, id);
    }

}
