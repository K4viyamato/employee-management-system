/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package techcompany;

/**
 *
 * @author kavindu
 */
public class designationData {
    
    private int designationID;
    private String designationName;
    
    public designationData(int designationID, String designationName) {
        this.designationID = designationID;
        this.designationName = designationName;
    }
    
     public int getDesignationID() {
        return designationID;
    }

    public String getDesignationName() {
        return designationName;
    }
    
}
