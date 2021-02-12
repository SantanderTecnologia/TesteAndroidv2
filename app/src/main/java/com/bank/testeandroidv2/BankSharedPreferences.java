package com.bank.testeandroidv2;import android.content.Context;import android.content.SharedPreferences;public class BankSharedPreferences {    private SharedPreferences pref;    private SharedPreferences.Editor editor;    // Context    private Context _context;    // Shared pref mode    int PRIVATE_MODE = 0;    // Sharedpref file name    private static final String PREF_NAME = "pref";    private static final String USER = "user";    private static final String PASSWORD = "password";    public BankSharedPreferences(Context context) {        this._context = context;        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);        editor = pref.edit();    }        /**     *      * @param user     */    private void saveUSER(String user) {        editor.putString(USER,user);        editor.commit();    }    private String getUSER(){        return pref.getString(USER, "");    }    private void resetUSER(){        editor.remove(USER);        editor.commit();    }    /**     *      * @param password     */    private void savePASSWORD(String password) {        editor.putString(PASSWORD,password);        editor.commit();    }    private String getPASSWORD(){        return pref.getString(PASSWORD, "");    }    private void resetPASSWORD(){        editor.remove(PASSWORD);        editor.commit();    }    /**     * *****************************************     */    /**     * Save date of user to own sharedpref     * @param user     */    public void saveUserToSharedpreference(String user) {        String userString = user;        saveUSER(userString);    }/**     * Retrieving user from sharepref     */    public String getUserFromSharedPreference() {        return getUSER();    }    /**     * Reset all user     */    public void resetUserFromSharedPreference() {        resetUSER();    }    /**     * Save password to own sharedpref     * @param password     */    public void savePasswordToSharedpreference(String password) {        savePASSWORD(password);    }    /**     * Retrieving password from sharepref     */    public String getPasswordFromSharedPreference() {        return getPASSWORD();    }    /**     * Reset all password     */    public void resetPasswordFromSharedPreference() {        resetPASSWORD();    }        public void resetAll() {        resetUserFromSharedPreference();        resetPasswordFromSharedPreference();    }}