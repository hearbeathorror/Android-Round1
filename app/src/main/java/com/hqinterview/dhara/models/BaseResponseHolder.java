package com.hqinterview.dhara.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by USER on 23-07-2015.
 */
public class BaseResponseHolder implements Serializable{
    @SerializedName("index")
    private Module base;
    @SerializedName("faq")
    private Module faq;
    @SerializedName("roomsinfo")
    private Module roomsInfo;
    @SerializedName("shaking")
    private Module shaking;
    @SerializedName("support")
    private Module support;
    @SerializedName("priceGuaranteeOverview")
    private Module priceGuaranteeOverview;
    @SerializedName("priceGuaranteeDetail")
    private Module priceGuaranteeDetail;
    @SerializedName("terms")
    private Module terms;
    @SerializedName("termsReferrals")
    private Module termsReferrals;
    @SerializedName("howDoesItWork")
    private Module howDoesItWork;
    @SerializedName("jobs")
    private Module jobs;
    @SerializedName("forHotels")
    private Module forHotels;
    @SerializedName("policy")
    private Module policy;
    @SerializedName("securityInfo")
    private Module securityInfo;
    @SerializedName("creditsEmpty")
    private Module creditsEmpty;
    @SerializedName("creditsList")
    private Module creditsList;
    @SerializedName("creditsListUsed")
    private Module creditsListUsed;
    @SerializedName("creditsListExpired")
    private Module creditsListExpired;
    @SerializedName("creditsOverview")
    private Module creditsOverview;
    @SerializedName("sharingDetails")
    private Module sharingDetails;
    @SerializedName("sharingOverview")
    private Module sharingOverview;
    @SerializedName("howToShare")
    private Module howToShare;
    @SerializedName("loyaltyProgram")
    private Module loyaltyProgram;
    @SerializedName("soldOut")
    private Module soldOut;
    @SerializedName("discount")
    private Module discount;
    @SerializedName("whySoFew")
    private Module whySoFew;
    @SerializedName("cityPromotion")
    private Module cityPromotion;
    @SerializedName("cannotRedeem")
    private Module cannotRedeem;
    @SerializedName("cityCoverage")
    private Module cityCoverage;
    @SerializedName("cityUnknown")
    private Module cityUnknown;
    @SerializedName("locationUnknown")
    private Module locationUnknown;
    @SerializedName("hotelquickly")
    private Module hotelquickly;
    @SerializedName("orderCalculation")
    private Module orderCalculation;
    @SerializedName("offerReviews")
    private Module offerReviews;
    @SerializedName("offerInfo")
    private Module offerInfo;
    @SerializedName("pointsOfInterest")
    private Module pointsOfInterest;
    @SerializedName("noCreditForOrder")
    private Module noCreditForOrder;
    @SerializedName("forcedRegistration")
    private Module forcedRegistration;
    @SerializedName("notificationList")
    private Module notificationList;
    @SerializedName("ordersSavings")
    private Module ordersSavings;
    @SerializedName("news")
    private Module news;
    @SerializedName("bestPrice")
    private Module bestPrice;
    @SerializedName("guestInfoRequired")
    private Module guestInfoRequired;
    @SerializedName("lineDemo")
    private Module lineDemo;

    public Module getFaq() {
        return faq;
    }

    public void setFaq(Module faq) {
        this.faq = faq;
    }

    public Module getRoomsInfo() {
        return roomsInfo;
    }

    public void setRoomsInfo(Module roomsInfo) {
        this.roomsInfo = roomsInfo;
    }

    public Module getShaking() {
        return shaking;
    }

    public void setShaking(Module shaking) {
        this.shaking = shaking;
    }

    public Module getSupport() {
        return support;
    }

    public void setSupport(Module support) {
        this.support = support;
    }

    public Module getPriceGuaranteeOverview() {
        return priceGuaranteeOverview;
    }

    public void setPriceGuaranteeOverview(Module priceGuaranteeOverview) {
        this.priceGuaranteeOverview = priceGuaranteeOverview;
    }

    public Module getPriceGuaranteeDetail() {
        return priceGuaranteeDetail;
    }

    public void setPriceGuaranteeDetail(Module priceGuaranteeDetail) {
        this.priceGuaranteeDetail = priceGuaranteeDetail;
    }

    public Module getTerms() {
        return terms;
    }

    public void setTerms(Module terms) {
        this.terms = terms;
    }

    public Module getTermsReferrals() {
        return termsReferrals;
    }

    public void setTermsReferrals(Module termsReferrals) {
        this.termsReferrals = termsReferrals;
    }

    public Module getHowDoesItWork() {
        return howDoesItWork;
    }

    public void setHowDoesItWork(Module howDoesItWork) {
        this.howDoesItWork = howDoesItWork;
    }

    public Module getJobs() {
        return jobs;
    }

    public void setJobs(Module jobs) {
        this.jobs = jobs;
    }

    public Module getForHotels() {
        return forHotels;
    }

    public void setForHotels(Module forHotels) {
        this.forHotels = forHotels;
    }

    public Module getPolicy() {
        return policy;
    }

    public void setPolicy(Module policy) {
        this.policy = policy;
    }

    public Module getSecurityInfo() {
        return securityInfo;
    }

    public void setSecurityInfo(Module securityInfo) {
        this.securityInfo = securityInfo;
    }

    public Module getCreditsEmpty() {
        return creditsEmpty;
    }

    public void setCreditsEmpty(Module creditsEmpty) {
        this.creditsEmpty = creditsEmpty;
    }

    public Module getCreditsList() {
        return creditsList;
    }

    public void setCreditsList(Module creditsList) {
        this.creditsList = creditsList;
    }

    public Module getCreditsListUsed() {
        return creditsListUsed;
    }

    public void setCreditsListUsed(Module creditsListUsed) {
        this.creditsListUsed = creditsListUsed;
    }

    public Module getCreditsListExpired() {
        return creditsListExpired;
    }

    public void setCreditsListExpired(Module creditsListExpired) {
        this.creditsListExpired = creditsListExpired;
    }

    public Module getCreditsOverview() {
        return creditsOverview;
    }

    public void setCreditsOverview(Module creditsOverview) {
        this.creditsOverview = creditsOverview;
    }

    public Module getSharingDetails() {
        return sharingDetails;
    }

    public void setSharingDetails(Module sharingDetails) {
        this.sharingDetails = sharingDetails;
    }

    public Module getSharingOverview() {
        return sharingOverview;
    }

    public void setSharingOverview(Module sharingOverview) {
        this.sharingOverview = sharingOverview;
    }

    public Module getHowToShare() {
        return howToShare;
    }

    public void setHowToShare(Module howToShare) {
        this.howToShare = howToShare;
    }

    public Module getLoyaltyProgram() {
        return loyaltyProgram;
    }

    public void setLoyaltyProgram(Module loyaltyProgram) {
        this.loyaltyProgram = loyaltyProgram;
    }

    public Module getSoldOut() {
        return soldOut;
    }

    public void setSoldOut(Module soldOut) {
        this.soldOut = soldOut;
    }

    public Module getDiscount() {
        return discount;
    }

    public void setDiscount(Module discount) {
        this.discount = discount;
    }

    public Module getWhySoFew() {
        return whySoFew;
    }

    public void setWhySoFew(Module whySoFew) {
        this.whySoFew = whySoFew;
    }

    public Module getCityPromotion() {
        return cityPromotion;
    }

    public void setCityPromotion(Module cityPromotion) {
        this.cityPromotion = cityPromotion;
    }

    public Module getCannotRedeem() {
        return cannotRedeem;
    }

    public void setCannotRedeem(Module cannotRedeem) {
        this.cannotRedeem = cannotRedeem;
    }

    public Module getCityCoverage() {
        return cityCoverage;
    }

    public void setCityCoverage(Module cityCoverage) {
        this.cityCoverage = cityCoverage;
    }

    public Module getCityUnknown() {
        return cityUnknown;
    }

    public void setCityUnknown(Module cityUnknown) {
        this.cityUnknown = cityUnknown;
    }

    public Module getLocationUnknown() {
        return locationUnknown;
    }

    public void setLocationUnknown(Module locationUnknown) {
        this.locationUnknown = locationUnknown;
    }

    public Module getHotelquickly() {
        return hotelquickly;
    }

    public void setHotelquickly(Module hotelquickly) {
        this.hotelquickly = hotelquickly;
    }

    public Module getOrderCalculation() {
        return orderCalculation;
    }

    public void setOrderCalculation(Module orderCalculation) {
        this.orderCalculation = orderCalculation;
    }

    public Module getOfferReviews() {
        return offerReviews;
    }

    public void setOfferReviews(Module offerReviews) {
        this.offerReviews = offerReviews;
    }

    public Module getOfferInfo() {
        return offerInfo;
    }

    public void setOfferInfo(Module offerInfo) {
        this.offerInfo = offerInfo;
    }

    public Module getPointsOfInterest() {
        return pointsOfInterest;
    }

    public void setPointsOfInterest(Module pointsOfInterest) {
        this.pointsOfInterest = pointsOfInterest;
    }

    public Module getNoCreditForOrder() {
        return noCreditForOrder;
    }

    public void setNoCreditForOrder(Module noCreditForOrder) {
        this.noCreditForOrder = noCreditForOrder;
    }

    public Module getForcedRegistration() {
        return forcedRegistration;
    }

    public void setForcedRegistration(Module forcedRegistration) {
        this.forcedRegistration = forcedRegistration;
    }

    public Module getNotificationList() {
        return notificationList;
    }

    public void setNotificationList(Module notificationList) {
        this.notificationList = notificationList;
    }

    public Module getOrdersSavings() {
        return ordersSavings;
    }

    public void setOrdersSavings(Module ordersSavings) {
        this.ordersSavings = ordersSavings;
    }

    public Module getNews() {
        return news;
    }

    public void setNews(Module news) {
        this.news = news;
    }

    public Module getBestPrice() {
        return bestPrice;
    }

    public void setBestPrice(Module bestPrice) {
        this.bestPrice = bestPrice;
    }

    public Module getGuestInfoRequired() {
        return guestInfoRequired;
    }

    public void setGuestInfoRequired(Module guestInfoRequired) {
        this.guestInfoRequired = guestInfoRequired;
    }

    public Module getLineDemo() {
        return lineDemo;
    }

    public void setLineDemo(Module lineDemo) {
        this.lineDemo = lineDemo;
    }

    public Module getBase() {
        return base;
    }

    public void setBase(Module base) {
        this.base = base;
    }
}