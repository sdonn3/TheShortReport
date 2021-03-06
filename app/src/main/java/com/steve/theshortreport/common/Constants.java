package com.steve.theshortreport.common;

/**
 * Created by steve-donnelly on 3/22/15.
 */
public interface Constants {
    String weatherURL = "http://api.openweathermap.org/data/2.5";
    String weatherAppErrorTag = "weather.error";

    int CAN_WEAR_SHORTS = 0;
    int MAYBE_WEAR_SHORTS = 1;
    int CANNOT_WEAR_SHORTS = 2;

    String DEFAULT_YES_THRESHOLD = "70";
    String DEFAULT_MAYBE_THRESHOLD = "65";
    boolean DEFAULT_IS_RAIN_A_FACTOR = true;

    String[] shortsPhrases = {
            "A simple life is good with me. I don't need a whole lot. For me, a T-shirt, a pair of shorts, barefoot on a beach and I'm happy.\n- Yanni",
            "The perfect shorts are always important.\n- Britt Robertson",
            "Yes, U.S. travelers dress better. The British are always so conspicuous in hot climates. They don't seem to wear shorts. American men seem to be comfortable wearing hot-weather clothing.\n- Bill Bryson",
            "Shorts are practically a uniform in every woman's closet.\n- Trinny Woodall",
            "I am a California girl, born and raised, so flip-flops and cutoff shorts are my go-to look.\n- Meghan Markle",
            "Love is like a beautiful summer day in the middle of winter. I’ve got shorts on, so it seems like a good time to invade Russia.\n- Jarod Kintz",
            "I like shorts! They're comfortable and easy to wear!"
};

    String[] maybeShortsPhrases = {
            "I love shorts in the colder climates, because you can wear them with chunky sweaters and jackets. It's cute and funky.\n- Rachel Bilson",
            "I look good and that's all that matters. And when I die of hypothermia for wearing formal shorts in winter, tell them to put that on my tombstone.\n- Eliza Coupe",
            "The best fashion advice I'd say would be just to do what makes you comfortable...\n- Ariana Grande",
            "Be willing to be uncomfortable. Be comfortable being uncomfortable. It may get tough, but it's a small price to pay for living a dream.\n- Peter McWilliams"
    };
    String[] noShortsPhrases = {
            "A man should never wear shorts in the city. Flip-flops and shorts in the city are never appropriate. Shorts should only be worn on the tennis court or on the beach.\n- Tom Ford",
            "I love summer, but my legs are so pale I can never wear shorts or a bikini\n- Emma Roberts",
            "There are only three men in the world who are licensed to wear shorts: Brad Pitt, Johnny Depp and Tom Cruise\n- Bill Nighy",
            "I like playing a guy who wears pants as opposed to shorts.\n- Diedrich Bader",
            "I wish I could pull shorts off. My wife tells me that I can't. But that's okay. I'm tall, I can do other things, like change light bulbs.\n- Adam Driver",
            "People wear shorts to the Broadway theater. There should be a law against that.\n- Stanley Tucci",
            "Wearing: shorts + a jersey = a visual oxymoron.\n- Mokokoma Mokhonoana",
            "Men in shorts are disgusting.\n- Fran Lebowitz"
    };
}
