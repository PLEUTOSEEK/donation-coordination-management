/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import adt.RedBlackTree;
import com.bethecoder.ascii_table.ASCIITable;
import com.github.javafaker.Faker;
import io.github.benas.randombeans.randomizers.range.LocalDateTimeRangeRandomizer;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

/**
 *
 * @author Tee Zhuo Xuan
 */
public class DemandList implements Comparable<DemandList> {

    private String demandListID;
    private Campaign campaign;
    private String demandName;
    private int quantity;
    private double pricePerUnit;
    private String description;
    private int orgiQty;
    private LocalDate dateRegister;
    private Timestamp dateModified;
    private String status;
    private static String lastDemandID = "";

    public DemandList() {
    }

    public DemandList(String demandListID) {
        this.demandListID = demandListID;
    }

    public DemandList(String demandListID, Campaign campaign, String demandName, int quantity, double pricePerUnit, String description, int orgiQty, LocalDate dateRegister, Timestamp dateModified, String status) {
        this.demandListID = demandListID;
        this.campaign = campaign;
        this.demandName = demandName;
        this.quantity = quantity;
        this.pricePerUnit = pricePerUnit;
        this.description = description;
        this.orgiQty = orgiQty;
        this.dateRegister = dateRegister;
        this.dateModified = dateModified;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static String getLastDemandID() {
        return lastDemandID;
    }

    public static void setLastDemandID(String lastDemandID) {
        DemandList.lastDemandID = lastDemandID;
    }

    public String getDemandListID() {
        return demandListID;
    }

    public void setDemandListID(String demandListID) {
        this.demandListID = demandListID;
    }

    public Campaign getCampaign() {
        return campaign;
    }

    public void setCampaign(Campaign campaign) {
        this.campaign = campaign;
    }

    public String getDemandName() {
        return demandName;
    }

    public void setDemandName(String demandName) {
        this.demandName = demandName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(double pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getOrgiQty() {
        return orgiQty;
    }

    public void setOrgiQty(int orgiQty) {
        this.orgiQty = orgiQty;
    }

    public LocalDate getDateRegister() {
        return dateRegister;
    }

    public void setDateRegister(LocalDate dateRegister) {
        this.dateRegister = dateRegister;
    }

    public Timestamp getDateModified() {
        return dateModified;
    }

    public void setDateModified(Timestamp dateModified) {
        this.dateModified = dateModified;
    }

    @Override
    public int compareTo(DemandList o) {//ID
        if (this.demandListID.compareTo(o.demandListID) < 0) {
            return -1;
        } else if (this.demandListID.compareTo(o.demandListID) > 0) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public boolean equals(Object o) {

        if (o instanceof DemandList) {
            DemandList other = (DemandList) o;
            if (this.demandListID.equalsIgnoreCase(other.getDemandListID())) {
                return true;
            } else {
                return false;
            }
        }

        return false;
    }

    private static String[] demandHeaders() {
        String[] campaignHeaders = {"Demand List ID", "Demand Name", "Quantity", "Date Register", "Campaign ID", "Campaign Name", "Campaign Status", "Date Modified"};

        return campaignHeaders;
    }

    private String[] strArr() {
        return new String[]{demandListID, demandName, String.valueOf(quantity), this.dateRegister.toString(), this.campaign.getCampaignID(), this.campaign.getCampaignName(), this.campaign.getStatus(), this.dateModified.toLocalDateTime().toString()};
    }

    private static String[][] demandRows(RedBlackTree<LocalDate, DemandList> demandListDB) {
        DemandList[] demandLists = new DemandList[demandListDB.getAllList().getLength()];
        demandLists = demandListDB.getAllArrayList(demandLists);
        String[][] demandRows = new String[demandLists.length][];
        for (int i = 0; i < demandLists.length; i++) {
            demandRows[i] = demandLists[i].strArr();
        }
        return demandRows;
    }

    public static void demandTable(RedBlackTree<LocalDate, DemandList> demandListDB) {
        String[] demandHeader = DemandList.demandHeaders();
        String[][] demandData = DemandList.demandRows(demandListDB);

        ASCIITable.getInstance().printTable(demandHeader, demandData);
    }

    public String autoGenerateID() {
        String newCampaignID = "";
        int seq = 0;
        if (lastDemandID.equals("")) {
            newCampaignID = "DM1001";
        } else {
            seq = Integer.parseInt(lastDemandID.substring(2));
            seq += 1;

            newCampaignID = "DM" + seq;
        }

        lastDemandID = newCampaignID;

        return lastDemandID;
    }

    public RedBlackTree<LocalDate, DemandList> generateDummyDemandList(RedBlackTree<LocalDate, Campaign> campaignDB) {
        RedBlackTree<LocalDate, DemandList> dummyDemandList = new RedBlackTree<>();
        // fake generator
        Faker faker = new Faker();
        LocalDateTimeRangeRandomizer randomLDTR;
        LocalDateTime randomLDT;
        LocalDateTime minTime = LocalDateTime.of(2018, Month.JANUARY, 1, 00, 00, 00);
        LocalDateTime maxTime = LocalDateTime.of(2021, Month.DECEMBER, 31, 23, 59, 59);
        randomLDTR = LocalDateTimeRangeRandomizer.aNewLocalDateTimeRangeRandomizer(minTime, maxTime);

        String[] campaignName = "All for Love,Another Chance,Be Marvelous,Walk for Homeless,JOY Licious,Carnival of Love,Charities 4 All,Charities 4 You,Charity Time,Homeless Children Org,Hope Anonymous,Hunger Solution Inc,Smileys,Neighborhood Love,Home Sweeter,Home,Donation City,Spirit of Giving,Crits for Bits,Dispensin’ Donations,Tip of the Hats,DoNation,Charitable Charm,Charity Time,Acute Awareness,Fortunate Folks,Life of Giving,Love In Action,Giving Group,Raise Reason,Fundraisers to Heaven,Give For Good,Hope Anonymous,Lots of Dreams of Lost Dreams,Secret Family,Generous Hearts,Better life,Ignite helpers,Spread Smiles,Love Hands,Superheroes,Givers Achievers,Give a Little,Give for Good,Give to Life,The Goal Quest,Good Hearted,Gracious Givers,Grateful Gifts,Greater Purpose,Heart & Sole Fundraiser Walk,Heart for the Arts,Home Sweeter,Home Hope,Anonymous,Hope Givers,Inspiration For The World,Lots of Dreams,Love In Action,Loving Care Inc.,Meat Shots for Hungry,Mission Minded,No Violence Inc,Noble Nonprofits,Precious Gifts,Prepare Aware,Pure Active Love".split(",");
        String[] phoneNumbers = "07241-5129 ,032273-0752 ,037877-2205 ,039281-9315 ,(607) 2388705 ,+60 (0)3 7726-8363 ,08264-5366 ,034108-4730 ,+60 (0)3 8075-2323 ,+6012-420 6639 ,04-656-1823 ,032272-1975 ,037861-4688 ,07352-0450 ,037880-4998 ,+60 (0)84 217961 ,037804-3182 ,036273-6599 ,07-237-2931 ,(603) 40241167".split(",");
        String[] desc = "But starting with Bill Clinton's first victory in 1992, Democratic fortunes in the region have improved--slowly at first, and then more rapidly.||If you make the right choice the combination tells a story, it makes people think.\" Here are the two photos she's used to make the image above: The hope is to get people to \"stop and think about history, about the hidden and sometimes forgotten stories of where they live.\" It wasn't all that long ago that Europe's streets were a battlefield.||Still, for some period of months, the prospect of the nuclear deal failing will be very frightening for the country’s rulers.||I bet you'd be surprised.||But behind this mask of ultra-confidence lies a fragile self-esteem that’s vulnerable to the slightest criticism.” One psychologist, Ben Michaelis, called Trump “textbook Narcissistic Personality Disorder.” Psychologist George Simon called Trump “so classic that I’m archiving video clips of him to use in workshops because there’s no better example of his characteristics.” To more wholly assess the claim of sociopathy, then, it may be more illustrative at this point to consider the Antisocial Personality Disorder side of the picture, which focuses on deceit, manipulation, disregard for the rights of others, and failure to take responsibility for one’s actions.||The government's cybersecurity position has been \"a lot of talk, not a lot of action, unfortunately, and people take their cues from that,\" Sen.||It spurred millions of readers to consider, for the first time, the impact of a meat-heavy diet on health and the environment.|| If I spend Bitcoin A, which I bought at $10, but is now worth $400, I’ve got a very different tax treatment than if I spend Bitcoin B, which I bought at $390.||No such buzz, or such strong reaction, has surrounded Walter Salles's unfairly maligned On the Road, a lovely adaptation of Jack Kerouac's classic beat-generation novel.||Since declaring his presidential candidacy, Sen.||Bloomberg singled out the Inspector General: As Capital New York reporter Azi Paybarah notes, \"The mayor suggested anonymous complaints about police offers are not currently investigated.\" It was inevitable that the police department at some point be forced to change its practices.||But that is of no great relevance to this piece which is basically about the Agency's role in the so-called \"war on terror\";||In a May 22nd opinion piece in the Los Angeles Times, Charlotte Allen transforms a profoundly varied series of beliefs into a single vulgar premise: Two X-chromosomes is evidently all it takes to make a woman -- the naiveté and cartoonish sexuality presumably come part and parcel.||\"By increasing taxes and creating new entitlements when we can't afford the ones we've got, the Obama administration has structurally weakened our economy.||If I was a hundred percent accurate I would have doubled the dick joke count.||(It's all explained by Google here.) In order to compare spikes in search, we based our scaling off of a term consistently Googled during 2011 -- \"Barack Obama\" -- but the choice is arbitrary.||Scroll down for the answer, or find it here.||Deprived of institutional supports in the medical community for burnout prevention, the experience of medical training detracts those who would try full-time practice, in particular primary care practice, from continuing.||Government by cliff is a recipe for disaster.\" 4:27 p.m.: Wyden out, Lee in.||Tom's not quite sure of that one.||Only a fraction of his millions of followers respond to these messages.||Illustration: Anje Jager||Probably the most significant proposal is the idea of requiring background checks for gun sales between private individuals, not just from licensed dealers (with some exceptions, such as transfers within a family).||And so has the onslaught of campaign ads.||Columbia was hardly the only library to junk its newspapers.||and the relatives of American citizens and legal permanent residents (about 800,000).||I'm afraid I'm going to have to ask you to leave.||But Muhammed had something to tell the world.||special forces in Syria, putting American lives at risk and compromising covert operations.||To say that the violence is caused by the laws that he enforces is a personal insult against his good intentions.".split("||");
        String[] demandNames = "Table,Chair,Plate,Bottle".split(",");
        // fake generator (END)

        Campaign[] campaigns = new Campaign[campaignDB.getAllList().getLength()];
        campaigns = campaignDB.getAllArrayList(campaigns);
        for (Campaign campaign : campaigns) {
            int randomDemand = faker.number().numberBetween(1, 3);
            for (int i = 0; i < randomDemand; i++) {
                DemandList demandList = new DemandList();
                demandList.setCampaign(campaign);
                demandList.setDemandListID(autoGenerateID());
                demandList.setDemandName(demandNames[faker.number().numberBetween(0, demandNames.length - 1)]);
                int qty = faker.number().numberBetween(1, 10);
                demandList.setOrgiQty(qty);
                demandList.setQuantity(qty);
                demandList.setPricePerUnit(faker.number().randomDouble(2, 100, 800));
                demandList.setDescription(desc[faker.number().numberBetween(0, desc.length - 1)]);
                demandList.setStatus("Active");
                demandList.setDateRegister(campaign.getCampaignRegisterDate().plusDays(faker.number().numberBetween(0, 3)));
                demandList.setDateModified(Timestamp.valueOf(demandList.getDateRegister().plusDays(faker.number().numberBetween(0, 3)).atStartOfDay()));
                dummyDemandList.addData(demandList.getDateRegister(), demandList);
            }
        }

        return dummyDemandList;
    }

}
