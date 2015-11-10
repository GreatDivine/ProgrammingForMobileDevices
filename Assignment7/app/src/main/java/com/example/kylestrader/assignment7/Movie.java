package com.example.kylestrader.assignment7;

/**
 * Created by kyle.strader on 11/9/2015.
 */
public class Movie {

    public String m_Title = "";
    public String m_Release = "";
    public String m_Description = "";

    public Movie(String m_Title, String m_Release, String m_Description) {
        this.m_Title = m_Title;
        this.m_Release = m_Release;
        this.m_Description = m_Description;
    }

    public String getM_Title() {
        return m_Title;
    }

    public void setM_Title(String m_Title) {
        this.m_Title = m_Title;
    }

    public String getM_Release() {
        return m_Release;
    }

    public void setM_Release(String m_Release) {
        this.m_Release = m_Release;
    }

    public String getM_Description() {
        return m_Description;
    }

    public void setM_Description(String m_Description) {
        this.m_Description = m_Description;
    }
}
