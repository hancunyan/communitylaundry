package com.computer.community_laundry.support.entity.laundry;

public class LaundryKnowledgeWithBLOBs extends LaundryKnowledge {
    private String knowledge;

    private String marketingactivities;

    public String getKnowledge() {
        return knowledge;
    }

    public void setKnowledge(String knowledge) {
        this.knowledge = knowledge == null ? null : knowledge.trim();
    }

    public String getMarketingactivities() {
        return marketingactivities;
    }

    public void setMarketingactivities(String marketingactivities) {
        this.marketingactivities = marketingactivities == null ? null : marketingactivities.trim();
    }
}