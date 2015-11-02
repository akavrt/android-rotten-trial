package com.akavrt.rotten;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Utils {
    private static final String TAG = Utils.class.getName();

    public static String readStringFromFileInAssets(Context context, String fileName) {
        InputStream is = null;
        try {
            is = context.getAssets().open(fileName);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));

            StringBuilder builder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
            return builder.toString();
        } catch (IOException e) {
            Log.e(TAG, e.getMessage(), e);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    //nothing to do
                }
            }
        }

        return null;
    }

    public static Movie createSampleMovie() {
        List<Movie> list = createSampleMovieList().movies;
        int index = new Random().nextInt(list.size());
        return list.get(index);
    }

    public static MovieList createSampleMovieList() {
        Movie movie;
        List<Movie> list = new ArrayList<>();

        movie = new Builder()
                .title("Burnt")
                .year(2015)
                .synopsis("Chef Adam Jones had it all - and lost it. A two-star Michelin " +
                        "rockstar with the bad habits to match, the former enfant terrible of " +
                        "the Paris restaurant scene did everything different every time out, " +
                        "and only ever cared about the thrill of creating explosions of taste. " +
                        "To land his own kitchen and that third elusive Michelin star though, " +
                        "he'll need the best of the best on his side, including the beautiful " +
                        "Helene. This remarkably funny and emotional story is about the love of " +
                        "food, the love between two people, and the power of second chances.")
                .score(-1, -1)
//                .score(35, 94)
                .poster("http://resizing.flixster.com/lgAt1PuEVUzXdEpuKG-izr4l2us=/54x80/" +
                        "dkpu1ddg7pbsk.cloudfront.net/movie/11/20/16/11201614_ori.jpg")
                .member("Bradley Cooper")
                .member("Sienna Miller")
                .member("Lily James")
                .build();
        list.add(movie);

        movie = new Builder()
                .title("Scouts Guide to the Zombie Apocalypse")
                .year(2015)
                .synopsis("Three friends who are scouts battle zombies and attempt to save " +
                        "their town from the undead.")
                .score(-1, 90)
                .poster("http://resizing.flixster.com/p7-mYL0udPKAmqr35ZobGOcb2Kg=/54x72/" +
                        "dkpu1ddg7pbsk.cloudfront.net/movie/11/20/02/11200260_ori.jpg")
                .member("Halston Sage")
                .member("Tye Sheridan")
                .member("Patrick Schwarzenegger")
                .build();
        list.add(movie);

        movie = new Builder()
                .title("Our Brand is Crisis")
                .year(2015)
                .synopsis("Sandra Bullock stars in this political comedy drama from Warner Bros. " +
                        "and director David Gordon Green, and inspired by the 2005 documentary " +
                        "of the same name, an expose of a bungled American election campaign in " +
                        "South America. ~ Jason Buchanan, Rovi")
                .score(90, -1)
                .poster("http://resizing.flixster.com/ecAekq4Vg6czDz2Yx4NhS-5Tkvc=/54x72/" +
                        "dkpu1ddg7pbsk.cloudfront.net/movie/11/20/23/11202343_ori.jpg")
                .member("Sandra Bullock")
                .member("Billy Bob Thornton")
                .member("Anthony Mackie")
                .build();
        list.add(movie);

        movie = new Builder()
                .title("The Wonders")
                .year(2015)
                .synopsis("Rohrwacher's richly textured sophomore feature centers on a family " +
                        "of beekeepers living in stark isolation in central Italy. The dynamic " +
                        "of their overcrowded household is disrupted by the simultaneous " +
                        "arrival of a silently troubled teenaged boy taken in as a farmhand, " +
                        "and a production crew recruiting local farmers to participate in a " +
                        "cheesy televised celebration of ancient Etruscan culture presented by " +
                        "the mysterious Milly Catena (Monica Bellucci). Both intrusions are of " +
                        "particular interest to the eldest daughter, Gelsomina, who is " +
                        "struggling to find her footing in the world, and Rohrwacher manages to " +
                        "convey her adolescent sense of wonder and confusion with " +
                        "characteristically graceful naturalism.")
                .score(96, 75)
                .poster("http://resizing.flixster.com/vsQEi6rtj4TfZfjqUz4y2t4HdfU=/54x79/" +
                        "dkpu1ddg7pbsk.cloudfront.net/movie/11/20/21/11202157_ori.jpg")
                .member("Alba Rohrwacher")
                .member("Maria Alexandra Lungu")
                .member("Sam Louwyck")
                .build();
        list.add(movie);

        movie = new Builder()
                .title("Love")
                .year(2015)
                .synopsis("Love materializes in a handful of different guises in L-O-V-E, a " +
                        "Taiwanese omnibus drama with episodes directed by neophytes Vincent " +
                        "Fang, Mickey Huang, Giddens Ko and Jem Chen. The feature opens with " +
                        "Ko's segment, the P.S. I Love You-inspired tale of a young man (Fan " +
                        "Van) faced with his own premature death, who embarks on a quest to " +
                        "find another male with the same voice as himself who can comfort his " +
                        "girlfriend (Megan Lai) in his own absence. As helmed by Fang, the " +
                        "second segment stars Annie Liu as a tour guide who risks forgetting " +
                        "about her music video director boyfriend. The third segment, by Chen, " +
                        "dramatizes the emotional vicissitudes of a couple (Alice Tseng and " +
                        "Ethan Ruan) teetering on the verge of a significant break-up. And the " +
                        "fourth and final installment, a rollicking comedy by Huang, stars " +
                        "Tracy Chow as the consummate nerd who employs truly bizarre methods " +
                        "when looking for romance. ~ Nathan Southern, Rovi")
                .score(53, 59)
                .poster("http://resizing.flixster.com/6yR1GUT6YbL6vNwbzvUWi7xxbIE=/54x76/" +
                        "dkpu1ddg7pbsk.cloudfront.net/movie/11/19/13/11191346_ori.jpg")
                .member("Aomi Muyock")
                .member("Klara Kristin")
                .member("Juan Saavedra")
                .build();
        list.add(movie);

        movie = new Builder()
                .title("Oklahoma!")
                .year(2015)
                .synopsis("Rodgers and Hammerstein's 1943 Broadway musical was considered " +
                        "revolutionary for a multitude of reasons, not least of which were the " +
                        "play's intricate integration of song and storyline, and the simplicity " +
                        "and austerity of its production design. The 1955 film version of " +
                        "Oklahoma! retains the songs (except for Lonely Room and It's a " +
                        "Scandal!, which are usually cut from most stage presentations anyway) " +
                        "and the story, but the simplicity is sacrificed to the spectacle of " +
                        "Technicolor, Todd-AO, and Stereophonic Sound. The story can be boiled " +
                        "down to a single sentence: a girl must decide between the two suitors " +
                        "who want to take her to a social. In her movie debut, 19-year-old " +
                        "Shirley Jones plays Laurie, an Oklahoma farm gal who is courted by " +
                        "boisterous cowboy Curley (Gordon MacRae) and by menacing, obsessive " +
                        "farm hand Jud Frye (Rod Steiger). Fearing that Jud will do something " +
                        "terrible to Curley, Laurie accepts Jud's invitation to the box social. " +
                        "But it's Curley who rescues Laurie from Jud's unwanted advances, and " +
                        "in so doing wins her hand. On the eve of their wedding, Laurie and " +
                        "Curley are menaced by the drunken Jud. During a fight with Curley, Jud " +
                        "falls on his own knife and is killed (this sudden-death motif was " +
                        "curiously commonplace in the Rodgers and Hammerstein ouevre). The " +
                        "local deputy insists that Curley be arrested and stand trial, but he " +
                        "is outvoted by Curley's friends, and the newlyweds are permitted to " +
                        "ride off on their honeymoon. Counterpointing the serious elements of " +
                        "the story is a comic subplot involving innocently promiscuous Ado " +
                        "Annie (Gloria Grahame), her erstwhile sweetheart Will Parker (Gene " +
                        "Nelson) and lascivious travelling salesman Ali Hakim (Eddie Albert). " +
                        "None of the Broadway cast of Oklahoma! was engaged for the film " +
                        "version, though Charlotte Greenwood is finally able to essay the role " +
                        "of Auntie Eller that had been written for her but she'd been unable to " +
                        "play back in 1943. The evergreen songs include Oh, What a Beautiful " +
                        "Mornin', Surrey with the Fringe on Top, People Will Say We're In Love, " +
                        "I Cain't Say No, and the rousing title song. Two versions of Oklahoma! " +
                        "currently exist: the Todd-AO version, filmed on 65-millimeter stock, " +
                        "and the simultaneously shot CinemaScope version, shipped out to the " +
                        "theaters not equipped for the wider-screen Todd-AO process. Both " +
                        "versions have been issued in \"letterbox\" form on laser disc, and " +
                        "the subtle differences in performance style and camera angles in each " +
                        "and every scene are quite fascinating. ~ Hal Erickson, Rovi")
                .score(60, 60)
                .poster("http://resizing.flixster.com/2I_K8LWnsWAU8nTTw1-Frc8rgSE=/53x77/" +
                        "dkpu1ddg7pbsk.cloudfront.net/movie/11/16/49/11164955_ori.jpg")
                .member("Gordon MacRae")
                .member("Shirley Jones")
                .member("Gloria Grahame")
                .build();
        list.add(movie);

        movie = new Builder()
                .title("Spectre")
                .year(2015)
                .synopsis("A cryptic message from Bond's past sends him on a trail to uncover a " +
                        "sinister organisation. While M battles political forces to keep the " +
                        "secret service alive, Bond peels back the layers of deceit to reveal " +
                        "the terrible truth behind Spectre.")
                .score(59, 99)
                .poster("http://resizing.flixster.com/52shS-7jfsIlzooulG4O021V1sQ=/54x80/" +
                        "dkpu1ddg7pbsk.cloudfront.net/movie/11/20/33/11203374_ori.jpg")
                .member("Daniel Craig")
                .member("Christoph Waltz")
                .member("Lea Seydoux")
                .build();
        list.add(movie);

        movie = new Builder()
                .title("The Peanuts Movie")
                .year(2015)
                .synopsis("Charlie Brown, Snoopy, Lucy, Linus and the rest of the beloved " +
                        "\"Peanuts\" gang make their big-screen debut, like they've never " +
                        "been seen before, in state of the art 3D animation. Charlie Brown, the " +
                        "world's most beloved underdog, embarks upon an epic and heroic quest, " +
                        "while his best pal, the lovable beagle Snoopy, takes to the skies to " +
                        "pursue his arch-nemesis, the Red Baron. From the imagination of " +
                        "Charles M. Schulz and the creators of the ICE AGE films, THE PEANUTS " +
                        "MOVIE will prove that every underdog has his day. (C) Fox")
                .score(99, 59)
                .poster("http://resizing.flixster.com/v93Rn6_ysacfO0sVDyWe2-0ZPrs=/54x80/" +
                        "dkpu1ddg7pbsk.cloudfront.net/movie/11/19/14/11191451_ori.jpg")
                .member("Noah Schnapp")
                .member("Hadley Belle Miller")
                .member("Alexander Garfin")
                .build();
        list.add(movie);

        movie = new Builder()
                .title("The Outskirts")
                .year(2015)
                .synopsis("Best friends Jodi and Mindy have suffered years of torment from the " +
                        "school's alpha female. They plan to ride out their senior year under " +
                        "the radar, but when they become the victim of a humiliating prank, " +
                        "they hatch a plan to unite the outcasts of the school and start a " +
                        "social revolution.")
                .score(-1, 81)
                .poster("http://resizing.flixster.com/3ThAukkG-IwfodrKhxeoU9Yv6EQ=/54x80/" +
                        "dkpu1ddg7pbsk.cloudfront.net/movie/11/20/21/11202155_ori.jpg")
                .member("Avan Jogia")
                .member("Eden Sher")
                .member("Peyton List")
                .build();
        list.add(movie);

        movie = new Builder()
                .title("Spotlight")
                .year(2015)
                .synopsis("SPOTLIGHT tells the riveting true story of the Pulitzer " +
                        "Prize-winning Boston Globe investigation that would rock the city and " +
                        "cause a crisis in one of the world's oldest and most trusted " +
                        "institutions. When the newspaper's tenacious \"Spotlight\" team of " +
                        "reporters delves into allegations of abuse in the Catholic Church, " +
                        "their year-long investigation uncovers a decades-long cover-up at the " +
                        "highest levels of Boston's religious, legal, and government " +
                        "establishment, touching off a wave of revelations around the world. " +
                        "Directed by Academy Award-nominee Tom McCarthy, SPOTLIGHT is a tense " +
                        "investigative dramatic-thriller, tracing the steps to one of the " +
                        "biggest cover-ups in modern times. (C) Open Road")
                .score(97, 96)
                .poster("http://resizing.flixster.com/9QwldYplyjEBxs7q3oHpxnHMyZY=/54x80/" +
                        "dkpu1ddg7pbsk.cloudfront.net/movie/11/20/15/11201558_ori.jpg")
                .member("Michael Keaton")
                .member("Rachel McAdams")
                .member("Stanley Tucci")
                .build();
        list.add(movie);

        movie = new Builder()
                .title("Brooklyn")
                .year(2015)
                .synopsis("BROOKLYN tells the profoundly moving story of Eilis Lacey (Saoirse " +
                        "Ronan), a young Irish immigrant navigating her way through 1950s " +
                        "Brooklyn. Lured by the promise of America, Eilis departs Ireland and " +
                        "the comfort of her mother's home for the shores of New York City. The " +
                        "initial shackles of homesickness quickly diminish as a fresh romance " +
                        "sweeps Eilis into the intoxicating charm of love. But soon, her new " +
                        "vivacity is disrupted by her past, and Eilis must choose between two " +
                        "countries and the lives that exist within. (C) Fox Searchlight")
                .score(100, 95)
                .poster("http://resizing.flixster.com/k28wtu-suNnah-2fJLUd9Kc_sc0=/54x80/" +
                        "dkpu1ddg7pbsk.cloudfront.net/movie/11/20/19/11201971_ori.jpg")
                .member("Saoirse Ronan")
                .member("Domhnall Gleeson")
                .member("Emory Cohen")
                .build();
        list.add(movie);

        movie = new Builder()
                .title("Trumbo")
                .year(2015)
                .synopsis("The successful career of 1940s screenwriter Dalton Trumbo comes to a " +
                        "crushing end when he and other Hollywood figures are blacklisted for " +
                        "their political beliefs. This film tells the story of his fight " +
                        "against the U.S. government and studio bosses in a war over words and " +
                        "freedom, which entangled everyone in Hollywood from Hedda Hopper and " +
                        "John Wayne to Kirk Douglas and Otto Preminger.")
                .score(83, 96)
                .poster("http://resizing.flixster.com/MnAvic7MjQr527o5Wx2e-aJTDyA=/54x80/" +
                        "dkpu1ddg7pbsk.cloudfront.net/movie/11/19/18/11191857_ori.jpg")
                .member("Bryan Cranston")
                .member("Alan Tudyk")
                .member("Diane Lane")
                .build();
        list.add(movie);

        movie = new Builder()
                .title("Miss You Already")
                .year(2015)
                .synopsis("An honest and powerful story following two best friends, Milly and " +
                        "Jess, as they navigate life's highs and lows. Inseparable since they " +
                        "were young girls, they can't remember a time they didn't share " +
                        "everything -- secrets, clothes, even boyfriends -- but nothing " +
                        "prepares them for the day Milly is hit with life-altering news.")
                .score(69, 91)
                .poster("http://resizing.flixster.com/wM41PF65UnZUWoTMmnLy65zT2CA=/54x80/" +
                        "dkpu1ddg7pbsk.cloudfront.net/movie/11/20/31/11203180_ori.jpg")
                .member("Drew Barrymore")
                .member("Toni Collette")
                .member("Dominic Cooper")
                .build();
        list.add(movie);

        movie = new Builder()
                .title("The Hallow (The Woods)")
                .year(2015)
                .synopsis("Deep within the darkness of secluded forest land in rural Ireland " +
                        "dwells an ancient evil. Feared by the nearby superstitious villagers " +
                        "as cursed creatures who prey upon the lost, their secrets have been " +
                        "kept from civilization and remain on their hallowed ground. But when a " +
                        "conservationist from London moves in with his wife and infant child in " +
                        "order to survey the land for future construction, his actions " +
                        "unwittingly disturb the horde of demonic forces. Alone in a remote " +
                        "wilderness, he must now ensure his family's survival from their " +
                        "relentless attacks. -- (C) IFC Midnight")
                .score(88, 90)
                .poster("http://resizing.flixster.com/hePADLHBDNgOy6ZUV7_dwTmWP4M=/54x80/" +
                        "dkpu1ddg7pbsk.cloudfront.net/movie/11/20/17/11201745_ori.jpg")
                .member("Bojana Novakovic")
                .member("Charlotte Williams")
                .member("Joseph Mawle")
                .build();
        list.add(movie);

        movie = new Builder()
                .title("Theeb")
                .year(2015)
                .synopsis("In the Ottoman province of Hijaz during World War I, a young Bedouin " +
                        "boy experiences a greatly hastened coming of age as he embarks on a " +
                        "perilous desert journey to guide a British officer to his secret " +
                        "destination.")
                .score(100, 76)
                .poster("http://resizing.flixster.com/-Ae8UkXKvLc7jKJnJdfxT6ZeNTg=/54x77/" +
                        "dkpu1ddg7pbsk.cloudfront.net/movie/11/18/93/11189356_ori.jpg")
                .member("Jacir Eid Al-Hwietat")
                .member("Hussein Salameh Al-Sweilhiyeen")
                .member("Hassan Mutlag Al-Maraiyeh")
                .build();
        list.add(movie);

        MovieList result = new MovieList();
        result.total = list.size();
        result.movies.addAll(list);

        return result;
    }

    public static class Builder {
        private String title;
        private int year;
        private String synopsis;
        private String posterUrl;

        private int criticsScore;
        private int audienceScore;

        private final List<Member> cast = new ArrayList<>();

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder year(int year) {
            this.year = year;
            return this;
        }

        public Builder synopsis(String synopsis) {
            this.synopsis = synopsis;
            return this;
        }

        public Builder poster(String url) {
            this.posterUrl = url;
            return this;
        }

        public Builder score(int critics, int audience) {
            this.criticsScore = critics;
            this.audienceScore = audience;
            return this;
        }

        public Builder member(String name) {
            Member member = new Member();
            member.name = name;
            this.cast.add(member);

            return this;
        }

        public Movie build() {
            Movie movie = new Movie();

            movie.title = this.title;
            movie.year = this.year;
            movie.synopsis = this.synopsis;

            Ratings ratings = new Ratings();
            ratings.critics_score = this.criticsScore;
            ratings.audience_score = this.audienceScore;
            movie.ratings = ratings;

            Posters posters = new Posters();
            posters.thumbnail = posterUrl;
            posters.profile = posterUrl;
            posters.detailed = posterUrl;
            posters.original = posterUrl;

            movie.posters = posters;

            movie.cast.addAll(this.cast);

            return movie;
        }
    }
}
