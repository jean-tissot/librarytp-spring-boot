/* -----------------------------------------
 * TP PRWEB - Spring
 *
 * Ecole Centrale Nantes
 * Jean-Yves MARTIN, Jean-Marie NORMAND
 * ----------------------------------------- */
package ecn.librarytp.controllers;

/**
 *
 * @author ECN
 */
public class MyUser {
    private String user;
    private String passwd;

    public MyUser() {
        this.user = null;
        this.passwd = null;
    }

    public MyUser(String user, String passwd) {
        this.user = user;
        this.passwd = passwd;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }
}
