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
public class adminData {
    
    private String username;
    private String role;
    
    public adminData(String username, String role) {
        this.username = username;
        this.role = role;
    }
    
    public String getAdminUsername() {
        return username;
    }

    public String getAdminUserRole() {
        return role;
    }
}

