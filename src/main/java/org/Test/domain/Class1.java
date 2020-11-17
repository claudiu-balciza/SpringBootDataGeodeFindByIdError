package org.Test.domain;

import org.apache.geode.pdx.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.gemfire.mapping.annotation.Region;

import java.util.ArrayList;
import java.util.HashMap;

@Region("TestRegion")
public class Class1 implements PdxSerializable {
  @Id
  Integer id;
  ArrayList<Integer> someList;
  HashMap<Integer, String> someMap;

  public Class1() {}

  public Class1(Integer id, ArrayList<Integer> someList, HashMap<Integer, String> someMap) {
    this.id = id;
    this.someList = someList;
    this.someMap = someMap;
  }

  @Override
  public String toString() {
    String ret;

    ret = "Class1";
    ret += "{id=";
    if (id == null) { ret += "null"; } else { ret += id.toString(); }
    ret += ", someList=";
    if (someList == null) { ret += "null"; } else { ret += someList.toString(); }
    ret += ", someMap=";
    if (someMap == null) { ret += "null"; } else { ret += someMap.toString(); }
    ret += "}";

    return ret;
  }

  @Override
  public void toData(PdxWriter out) throws PdxFieldAlreadyExistsException, PdxSerializationException {
    out.writeInt("id", id);
    out.writeObject("someList", someList);
    out.writeObject("someMap", someMap);
  }

  @Override
  public void fromData(PdxReader in) throws PdxFieldTypeMismatchException, PdxSerializationException {
    id = in.readInt("id");
    someList = (ArrayList<Integer>)in.readObject("someList");
    someMap = (HashMap<Integer, String>)in.readObject("someMap");
  }
}
