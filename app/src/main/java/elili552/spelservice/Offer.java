package elili552.spelservice;

import java.util.HashMap;

public class Offer {
    private String mOfferId;
    private String mOfferThumbnail;
    private String mOfferTitle;
    private String mOfferDescription;
    private String mOfferText;
    private String mOfferThru;
    private String mOfferUrl;
    private String mOfferCompany;
    private String mOfferType;

    public Offer(HashMap<String, String> offerData) {
        super();
        mOfferId = offerData.get("id");
        mOfferThumbnail = offerData.get("thumbnail");
        mOfferTitle = offerData.get("title");
        mOfferDescription = offerData.get("description");
        mOfferText = offerData.get("text");
        mOfferThru = offerData.get("thru");
        mOfferUrl = offerData.get("url");
        mOfferCompany = offerData.get("company");
        mOfferType = offerData.get("offertype");
    }

    public String getOfferId() { return mOfferId; }
    public void setOfferId(String id) {mOfferId = id;}
    public String getOfferImage() {
        return mOfferThumbnail;
    }
    public void setOfferImage(String offerImageUrl) {
        mOfferThumbnail = offerImageUrl;
    }

    public String getOfferTitle() {
        return mOfferTitle;
    }
    public void setOfferTitle(String offerTitle) {
        mOfferTitle = offerTitle;
    }

    public String getOfferDescription() {
        return mOfferDescription;
    }
    public void setOfferDescription(String offerDescription) { mOfferDescription = offerDescription; }

    public String getOfferText() {
        return mOfferText;
    }
    public void setOfferText(String offerText) {
        mOfferText = offerText;
    }

    public String getOfferThru() {
        return mOfferThru;
    }
    public void setOfferThru(String offerThru) {
        mOfferThru = offerThru;
    }

    public String getOfferUrl() {
        return mOfferUrl;
    }
    public void setOfferUrl(String offerUrl) {
        mOfferUrl = offerUrl;
    }

    public String getOfferCompany() {
        return mOfferCompany;
    }
    public void setOfferCompany(String offerCompany) {
        mOfferUrl = offerCompany;
    }

    public String getOfferType() {
        return mOfferType;
    }
    public void setOfferType(String offerType) {
        mOfferUrl = offerType;
    }
}
