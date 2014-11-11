package inradar.linkedin.com.inradar;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by bili on 11/9/14.
 */
public class LinkedInProfile {
    private String mAccessToken;
    private String mMemberId;
    private String mFormattedFullName;
    private String mEmail;
    private String mPhoto;
    private String mHeadline;

    public LinkedInProfile() {
    }

    public LinkedInProfile(String jsonProfile) {
        try {
            JSONObject jsonObj = new JSONObject(jsonProfile);
            setAccessToken(jsonObj.optString("access_token"));
            setMemberId(jsonObj.optString("member_id"));
            setFormattedFullName(jsonObj.optString("formatted_full_name"));
            setEmail(jsonObj.optString("email"));
            setPhoto(jsonObj.optString("photo"));
            setHeadline(jsonObj.optString("headline"));

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public LinkedInProfile(String accessToken, String memberId, String formattedFullName, String email, String photo, String headline) {
        mAccessToken = accessToken;
        mMemberId = memberId;
        mFormattedFullName = formattedFullName;
        mEmail = email;
        mPhoto = photo;
        mHeadline = headline;
    }

    @Override
    public String toString() {
        return mAccessToken + " " + mMemberId + " " + mFormattedFullName + " " + mEmail + " " + mPhoto + " " + mHeadline;
    }

    public String getAccessToken() {
        return mAccessToken;
    }

    public void setAccessToken(String accessToken) {
        mAccessToken = accessToken;
    }

    public String getMemberId() {
        return mMemberId;
    }

    public void setMemberId(String memberId) {
        mMemberId = memberId;
    }

    public String getFormattedFullName() {
        return mFormattedFullName;
    }

    public void setFormattedFullName(String formattedFullName) {
        mFormattedFullName = formattedFullName;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public String getPhoto() {
        return mPhoto;
    }

    public void setPhoto(String photo) {
        mPhoto = photo;
    }

    public String getHeadline() {
        return mHeadline;
    }

    public void setHeadline(String headline) {
        mHeadline = headline;
    }
}
