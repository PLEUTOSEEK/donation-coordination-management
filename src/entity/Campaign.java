/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import adt.DoublyLinkedList;
import adt.ListInterface;
import adt.RedBlackTree;
import com.bethecoder.ascii_table.ASCIITable;
import com.github.javafaker.Faker;
import io.github.benas.randombeans.randomizers.range.LocalDateTimeRangeRandomizer;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;

/**
 *
 * @author Tee Zhuo Xuan
 */
public class Campaign implements Comparable<Campaign>, Cloneable {

    private String campaignID;
    private String campaignName;
    private LocalDate campaignStartDate;
    private LocalTime campaignStartTime;
    private LocalDate campaignEndDate;
    private LocalTime campaignEndTime;
    private double targetAmount;
    private String campaignEmail;
    private String campaignMobileNo;
    private String campaignAddress;
    private String campaignBankNo;
    private String description;
    private String status;
    private LocalDate campaignRegisterDate;
    private Timestamp dateModified;
    private static String lastCampaignID = "";

    public Campaign() {
        this("", "", null, null, null, null, 0.0, "", "", "", "", "", "", null, null);
    }

    public Campaign(String campaignID) {
        this.campaignID = campaignID;
    }

    public Campaign(String campaignID, String campaignName, LocalDate campaignStartDate, LocalTime campaignStartTime, LocalDate campaignEndDate, LocalTime campaignEndTime, double targetAmount, String campaignEmail, String campaignMobileNo, String campagnAddress, String campaignBankNo, String description, String status, LocalDate campaignRegisterDate, Timestamp dateModified) {
        this.campaignID = campaignID;
        this.campaignName = campaignName;
        this.campaignStartDate = campaignStartDate;
        this.campaignStartTime = campaignStartTime;
        this.campaignEndDate = campaignEndDate;
        this.campaignEndTime = campaignEndTime;
        this.targetAmount = targetAmount;
        this.campaignEmail = campaignEmail;
        this.campaignMobileNo = campaignMobileNo;
        this.campaignAddress = campagnAddress;
        this.campaignBankNo = campaignBankNo;
        this.description = description;
        this.status = status;
        this.campaignRegisterDate = campaignRegisterDate;
        this.dateModified = dateModified;
    }

    public static void setLastCampaignID(String lastCampaignID) {
        Campaign.lastCampaignID = lastCampaignID;
    }

    public String getCampaignAddress() {
        return campaignAddress;
    }

    public void setCampaignAddress(String campaignAddress) {
        this.campaignAddress = campaignAddress;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static String getLastCampaignID() {
        return lastCampaignID;
    }

    public String getCampaignID() {
        return campaignID;
    }

    public void setCampaignID(String campaignID) {
        this.campaignID = campaignID;
    }

    public String getCampaignName() {
        return campaignName;
    }

    public void setCampaignName(String campaignName) {
        this.campaignName = campaignName;
    }

    public LocalDate getCampaignStartDate() {
        return campaignStartDate;
    }

    public void setCampaignStartDate(LocalDate campaignStartDate) {
        this.campaignStartDate = campaignStartDate;
    }

    public LocalTime getCampaignStartTime() {
        return campaignStartTime;
    }

    public void setCampaignStartTime(LocalTime campaignStartTime) {
        this.campaignStartTime = campaignStartTime;
    }

    public LocalDate getCampaignEndDate() {
        return campaignEndDate;
    }

    public void setCampaignEndDate(LocalDate campaignEndDate) {
        this.campaignEndDate = campaignEndDate;
    }

    public LocalTime getCampaignEndTime() {
        return campaignEndTime;
    }

    public void setCampaignEndTime(LocalTime campaignEndTime) {
        this.campaignEndTime = campaignEndTime;
    }

    public double getTargetAmount() {
        return targetAmount;
    }

    public void setTargetAmount(double targetAmount) {
        this.targetAmount = targetAmount;
    }

    public String getCampaignEmail() {
        return campaignEmail;
    }

    public void setCampaignEmail(String campaignEmail) {
        this.campaignEmail = campaignEmail;
    }

    public String getCampaignMobileNo() {
        return campaignMobileNo;
    }

    public void setCampaignMobileNo(String campaignMobileNo) {
        this.campaignMobileNo = campaignMobileNo;
    }

    public String getCampagnAddress() {
        return campaignAddress;
    }

    public void setCampagnAddress(String campagnAddress) {
        this.campaignAddress = campagnAddress;
    }

    public String getCampaignBankNo() {
        return campaignBankNo;
    }

    public void setCampaignBankNo(String campaignBankNo) {
        this.campaignBankNo = campaignBankNo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getCampaignRegisterDate() {
        return campaignRegisterDate;
    }

    public void setCampaignRegisterDate(LocalDate campaignRegisterDate) {
        this.campaignRegisterDate = campaignRegisterDate;
    }

    public Timestamp getDateModified() {
        return dateModified;
    }

    public void setDateModified(Timestamp dateModified) {
        this.dateModified = dateModified;
    }

    @Override
    public int compareTo(Campaign o) {//ID
        if (this.campaignID.compareTo(o.campaignID) < 0) {
            return -1;
        } else if (this.campaignID.compareTo(o.campaignID) > 0) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public boolean equals(Object o) {

        if (o instanceof Campaign) {
            Campaign other = (Campaign) o;
            if (this.campaignID.equalsIgnoreCase(other.getCampaignID())) {
                return true;
            } else {
                return false;
            }
        }

        return false;
    }

    private static String[] campaignHeaders() {
        String[] campaignHeaders = {"Campaign ID", "Campaign Name", "Start Date", "End Date", "Email", "Bank No.", "Target Amount", "Status", "Date Modified"};

        return campaignHeaders;
    }

    private String[] strArr() {
        return new String[]{campaignID, campaignName, this.campaignStartDate.toString(), this.campaignEndDate.toString(), this.campaignEmail, this.campaignBankNo, String.valueOf(this.targetAmount), this.status, this.dateModified.toLocalDateTime().toString()};
    }

    private static String[][] campaignRows(RedBlackTree<LocalDate, Campaign> campaignList) {
        Campaign[] campaigns = new Campaign[campaignList.getAllList().getLength()];
        campaigns = campaignList.getAllListInArray(campaigns);
        String[][] campaignRows = new String[campaigns.length][];
        for (int i = 0; i < campaigns.length; i++) {
            campaignRows[i] = campaigns[i].strArr();
        }
        return campaignRows;
    }

    public static void campaignTable(RedBlackTree<LocalDate, Campaign> campaignList) {
        String[] header = Campaign.campaignHeaders();
        String[][] cmapaignData = Campaign.campaignRows(campaignList);

        ASCIITable.getInstance().printTable(header, cmapaignData);
    }

    public String autoGenerateID() {
        String newCampaignID = "";
        int seq = 0;
        if (lastCampaignID.equals("")) {
            newCampaignID = "C1001";
        } else {
            seq = Integer.parseInt(lastCampaignID.substring(1));
            seq += 1;

            newCampaignID = "C" + seq;
        }

        lastCampaignID = newCampaignID;

        return lastCampaignID;
    }

    public RedBlackTree<LocalDate, Campaign> generateDummyCampaign() {
        RedBlackTree<LocalDate, Campaign> dummyCampaign = new RedBlackTree<>();
        // fake generator
        Faker faker = new Faker();
        LocalDateTimeRangeRandomizer randomLDTR;
        LocalDateTime randomLDT;
        LocalDateTime minTime = LocalDateTime.of(2020, Month.JANUARY, 1, 00, 00, 00);
        LocalDateTime maxTime = LocalDateTime.of(2023, Month.DECEMBER, 31, 23, 59, 59);
        randomLDTR = LocalDateTimeRangeRandomizer.aNewLocalDateTimeRangeRandomizer(minTime, maxTime);

        String[] campaignName = "All for Love,Another Chance,Be Marvelous,Walk for Homeless,JOY Licious,Carnival of Love,Charities 4 All,Charities 4 You,Charity Time,Homeless Children Org,Hope Anonymous,Hunger Solution Inc,Smileys,Neighborhood Love,Home Sweeter,Home,Donation City,Spirit of Giving,Crits for Bits,Dispensin’ Donations,Tip of the Hats,DoNation,Charitable Charm,Charity Time,Acute Awareness,Fortunate Folks,Life of Giving,Love In Action,Giving Group,Raise Reason,Fundraisers to Heaven,Give For Good,Hope Anonymous,Lots of Dreams of Lost Dreams,Secret Family,Generous Hearts,Better life,Ignite helpers,Spread Smiles,Love Hands,Superheroes,Givers Achievers,Give a Little,Give for Good,Give to Life,The Goal Quest,Good Hearted,Gracious Givers,Grateful Gifts,Greater Purpose,Heart & Sole Fundraiser Walk,Heart for the Arts,Home Sweeter,Home Hope,Anonymous,Hope Givers,Inspiration For The World,Lots of Dreams,Love In Action,Loving Care Inc.,Meat Shots for Hungry,Mission Minded,No Violence Inc,Noble Nonprofits,Precious Gifts,Prepare Aware,Pure Active Love".split(",");
        String[] phoneNumbers = "07241-5129 ,032273-0752 ,037877-2205 ,039281-9315 ,(607) 2388705 ,+60 (0)3 7726-8363 ,08264-5366 ,034108-4730 ,+60 (0)3 8075-2323 ,+6012-420 6639 ,04-656-1823 ,032272-1975 ,037861-4688 ,07352-0450 ,037880-4998 ,+60 (0)84 217961 ,037804-3182 ,036273-6599 ,07-237-2931 ,(603) 40241167".split(",");
        String[] desc = "But starting with Bill Clinton's first victory in 1992, Democratic fortunes in the region have improved--slowly at first, and then more rapidly.||If you make the right choice the combination tells a story, it makes people think.\" Here are the two photos she's used to make the image above: The hope is to get people to \"stop and think about history, about the hidden and sometimes forgotten stories of where they live.\" It wasn't all that long ago that Europe's streets were a battlefield.||Still, for some period of months, the prospect of the nuclear deal failing will be very frightening for the country’s rulers.||I bet you'd be surprised.||But behind this mask of ultra-confidence lies a fragile self-esteem that’s vulnerable to the slightest criticism.” One psychologist, Ben Michaelis, called Trump “textbook Narcissistic Personality Disorder.” Psychologist George Simon called Trump “so classic that I’m archiving video clips of him to use in workshops because there’s no better example of his characteristics.” To more wholly assess the claim of sociopathy, then, it may be more illustrative at this point to consider the Antisocial Personality Disorder side of the picture, which focuses on deceit, manipulation, disregard for the rights of others, and failure to take responsibility for one’s actions.||The government's cybersecurity position has been \"a lot of talk, not a lot of action, unfortunately, and people take their cues from that,\" Sen.||It spurred millions of readers to consider, for the first time, the impact of a meat-heavy diet on health and the environment.|| If I spend Bitcoin A, which I bought at $10, but is now worth $400, I’ve got a very different tax treatment than if I spend Bitcoin B, which I bought at $390.||No such buzz, or such strong reaction, has surrounded Walter Salles's unfairly maligned On the Road, a lovely adaptation of Jack Kerouac's classic beat-generation novel.||Since declaring his presidential candidacy, Sen.||Bloomberg singled out the Inspector General: As Capital New York reporter Azi Paybarah notes, \"The mayor suggested anonymous complaints about police offers are not currently investigated.\" It was inevitable that the police department at some point be forced to change its practices.||But that is of no great relevance to this piece which is basically about the Agency's role in the so-called \"war on terror\";||In a May 22nd opinion piece in the Los Angeles Times, Charlotte Allen transforms a profoundly varied series of beliefs into a single vulgar premise: Two X-chromosomes is evidently all it takes to make a woman -- the naiveté and cartoonish sexuality presumably come part and parcel.||\"By increasing taxes and creating new entitlements when we can't afford the ones we've got, the Obama administration has structurally weakened our economy.||If I was a hundred percent accurate I would have doubled the dick joke count.||(It's all explained by Google here.) In order to compare spikes in search, we based our scaling off of a term consistently Googled during 2011 -- \"Barack Obama\" -- but the choice is arbitrary.||Scroll down for the answer, or find it here.||Deprived of institutional supports in the medical community for burnout prevention, the experience of medical training detracts those who would try full-time practice, in particular primary care practice, from continuing.||Government by cliff is a recipe for disaster.\" 4:27 p.m.: Wyden out, Lee in.||Tom's not quite sure of that one.||Only a fraction of his millions of followers respond to these messages.||Illustration: Anje Jager||Probably the most significant proposal is the idea of requiring background checks for gun sales between private individuals, not just from licensed dealers (with some exceptions, such as transfers within a family).||And so has the onslaught of campaign ads.||Columbia was hardly the only library to junk its newspapers.||and the relatives of American citizens and legal permanent residents (about 800,000).||I'm afraid I'm going to have to ask you to leave.||But Muhammed had something to tell the world.||special forces in Syria, putting American lives at risk and compromising covert operations.||To say that the violence is caused by the laws that he enforces is a personal insult against his good intentions.".split("||");
        // fake generator (END)
        Campaign campaign = new Campaign();

        for (int record = 0; record < 40; record++) {

            LocalDate startDate = randomLDTR.getRandomValue().toLocalDate();
            LocalDate endDate = startDate.plusDays(faker.number().numberBetween(1, 9));
            LocalTime startTime = randomLDTR.getRandomValue().toLocalTime();
            LocalTime endTime = startTime.plusHours(faker.number().numberBetween(1, 4));
            LocalDate registerDate = startDate.minusDays(faker.number().numberBetween(15, 30));
            Timestamp dateModified = new Timestamp(registerDate.plusDays(faker.number().numberBetween(4, 14)).toEpochDay());

            String address = faker.address().streetAddress() + ", "
                    + faker.address().city() + ", "
                    + faker.address().state() + ", "
                    + faker.address().zipCode() + ", "
                    + faker.address().country();

            String bankNo = (int) faker.number().randomDouble(0, 1000, 9000) + " "
                    + (int) faker.number().randomDouble(0, 1000, 9000) + " "
                    + (int) faker.number().randomDouble(0, 1000, 9000);

            campaign = new Campaign();
            campaign.setCampaignID(autoGenerateID());
            campaign.setCampaignName(campaignName[record]);
            campaign.setCampaignStartDate(startDate);
            campaign.setCampaignStartTime(startTime);
            campaign.setCampaignEndDate(endDate);
            campaign.setCampaignEndTime(endTime);
            campaign.setTargetAmount(faker.number().randomDouble(2, 5000, 10000));
            campaign.setCampaignEmail(faker.internet().emailAddress());
            campaign.setCampaignMobileNo(phoneNumbers[(int) faker.number().randomDouble(0, 0, phoneNumbers.length - 1)]);
            campaign.setCampaignAddress(address);
            campaign.setCampaignBankNo(bankNo);
            campaign.setDescription(desc[(int) faker.number().randomDouble(0, 0, desc.length - 1)]);
            campaign.setStatus("Active");
            campaign.setCampaignRegisterDate(registerDate);
            campaign.setDateModified(dateModified);

            dummyCampaign.addData(campaign.campaignStartDate, campaign);
        }

        return dummyCampaign;
    }

    @Override
    public Campaign clone() throws CloneNotSupportedException {
        Campaign cloned = (Campaign) super.clone();
        return cloned;
    }

    public boolean isPermanentDelete() {
        return status.equalsIgnoreCase("Permanent Inactive");
    }

    public boolean isInactive() {
        return status.equalsIgnoreCase("Inactive");
    }

    @Override
    public String toString() {
        return "Campaign{" + "campaignID=" + campaignID + ", campaignName=" + campaignName + ", campaignStartDate=" + campaignStartDate + ", campaignStartTime=" + campaignStartTime + ", campaignEndDate=" + campaignEndDate + ", campaignEndTime=" + campaignEndTime + ", targetAmount=" + targetAmount + ", campaignEmail=" + campaignEmail + ", campaignMobileNo=" + campaignMobileNo + ", campaignAddress=" + campaignAddress + ", campaignBankNo=" + campaignBankNo + ", description=" + description + ", status=" + status + ", campaignRegisterDate=" + campaignRegisterDate + ", dateModified=" + dateModified + '}';
    }

    public static void deactiveExpiredCampaign(RedBlackTree<LocalDate, Campaign> campaignDB) {
        ListInterface<Campaign> deactiveCampaignList = campaignDB.getAllSmallerNode(LocalDate.now());

        if (deactiveCampaignList instanceof DoublyLinkedList) {
            deactiveCampaignList = (DoublyLinkedList<Campaign>) deactiveCampaignList;

            for (int i = 0; i < deactiveCampaignList.getLength(); i++) {
                if (deactiveCampaignList.getAt(i) instanceof Campaign) {
                    ((Campaign) deactiveCampaignList.getAt(i)).status = "Inactive";
                }
            }
        }

    }

}
