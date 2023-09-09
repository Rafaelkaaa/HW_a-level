package org.example.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import org.example.craft.collections.CraftArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class Raider extends BaseEntity {

    String country;
    String goalRaid;
    int booty;
    String fullName;

    public Raider(@NonNull String country, String goalRaid, int booty, @NonNull String fullName) {
        setCountry(country);
        setGoalRaid(goalRaid);
        setBooty(booty);
        setFullName(fullName);
    }

    public void setCountry(@NonNull String country) {
        if (isCountryValid(country)) {
            this.country = country;
        }
    }

    public void setGoalRaid(String goalRaid) {
        if (isGoalRaidValid(goalRaid)) {
            this.goalRaid = goalRaid;
        }
    }

    public void setBooty(int booty) {
        if (isBootyValid(booty)) {
            this.booty = booty;
        }
    }

    public void setFullName(@NonNull String fullName) {
        if (isFullNameValid(fullName)) {
            this.fullName = fullName;
        }
    }

    private boolean isBootyValid(int booty) {
        if (booty >= 500 && booty <= 1_000_000) return true;
        throw new IllegalArgumentException("Booty should be from 500 till 1 000 000");
    }

    private boolean isFullNameValid(String fullName) {
        String regex = "^(?:[A-Z][a-z]*\\s){1}[A-Z][a-z]*$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(fullName);

        if (matcher.matches()) return true;
        throw new IllegalArgumentException("The full name should contain 2 words, both starting with a capital letter");
    }

    private boolean isGoalRaidValid(String GoalRaid) {
        if ((GoalRaid.length() >= 15 && GoalRaid.length() <= 500)) return true;
        else throw new IllegalArgumentException("Goal raid should contain from 15 till 500 characters");
    }

    private boolean isCountryValid(String country) {
        CraftArrayList<String> countries = new CraftArrayList<>({"afghanistan", "albania", "algeria", "andorra", "angola", "antigua and barbuda", "argentina", "armenia",
                "australia", "austria", "azerbaijan", "bahamas", "bahrain", "bangladesh", "barbados", "belarus",
                "belgium", "belize", "benin", "bhutan", "bolivia", "bosnia and herzegovina", "botswana", "brazil",
                "brunei", "bulgaria", "burkina faso", "burundi", "cabo verde", "cambodia", "cameroon", "canada",
                "central african republic", "chad", "chile", "china", "colombia", "comoros", "congo", "costa rica",
                "cote d'ivoire", "croatia", "cuba", "cyprus", "czech republic", "democratic republic of the congo",
                "denmark", "djibouti", "dominica", "dominican republic", "ecuador", "egypt", "el salvador",
                "equatorial guinea", "eritrea", "estonia", "eswatini", "ethiopia", "fiji", "finland", "france",
                "gabon", "gambia", "georgia", "germany", "ghana", "greece", "grenada", "guatemala", "guinea",
                "guinea-bissau", "guyana", "haiti", "honduras", "hungary", "iceland", "india", "indonesia", "iran",
                "iraq", "ireland", "israel", "italy", "jamaica", "japan", "jordan", "kazakhstan", "kenya", "kiribati",
                "korea, north", "korea, south", "kosovo", "kuwait", "kyrgyzstan", "laos", "latvia", "lebanon", "lesotho",
                "liberia", "libya", "liechtenstein", "lithuania", "luxembourg", "madagascar", "malawi", "malaysia",
                "maldives", "mali", "malta", "marshall islands", "mauritania", "mauritius", "mexico", "micronesia",
                "moldova", "monaco", "mongolia", "montenegro", "morocco", "mozambique", "myanmar", "namibia", "nauru",
                "nepal", "netherlands", "new zealand", "nicaragua", "niger", "nigeria", "north macedonia", "norway",
                "oman", "pakistan", "palau", "palestine", "panama", "papua new guinea", "paraguay", "peru", "philippines",
                "poland", "portugal", "qatar", "romania", "russia", "rwanda", "saint kitts and nevis", "saint lucia",
                "saint vincent and the grenadines", "samoa", "san marino", "sao tome and principe", "saudi arabia",
                "senegal", "serbia", "seychelles", "sierra leone", "singapore", "slovakia", "slovenia", "solomon islands",
                "somalia", "south africa", "south sudan", "spain", "sri lanka", "sudan", "suriname", "sweden", "switzerland",
                "syria", "taiwan", "tajikistan", "tanzania", "thailand", "timor-leste", "togo", "tonga", "trinidad and tobago",
                "tunisia", "turkey", "turkmenistan", "tuvalu", "uganda", "ukraine", "united arab emirates", "united kingdom",
                "united states of america", "uruguay", "uzbekistan", "vanuatu", "vatican city", "venezuela", "vietnam",
                "yemen", "zambia", "zimbabwe"};);

        if (countries.contains(country.toLowerCase())) return true;
        throw new IllegalArgumentException("Country should contain the full name of the actual country");
    }

    @Override
    public String toString() {
        return "Raider{" +
                "country='" + country + '\'' +
                ", goalRaid='" + goalRaid + '\'' +
                ", booty=" + booty +
                ", fullName='" + fullName + '\'' +
                ", id='" + getId() + '\'' +
                '}';
    }
}

