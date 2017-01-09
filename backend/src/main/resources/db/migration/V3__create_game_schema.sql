-- ACRONYMS
CREATE TABLE "acronyms"(
  "id" INT NOT NULL AUTO_INCREMENT,
  "acronym" VARCHAR NOT NULL,
  "meaning" VARCHAR NOT NULL
);
ALTER TABLE "acronyms" ADD CONSTRAINT "acronyms_id" PRIMARY KEY("id");

-- ANSWERS
CREATE TABLE "answers"(
  "id" UUID NOT NULL,
  "answer" VARCHAR NOT NULL,
  "user_id" VARCHAR NOT NULL,
  "votes" INT NOT NULL DEFAULT 0,
  "created_on" TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  "updated_on" TIMESTAMP
);
ALTER TABLE "answers" ADD CONSTRAINT "answers_id" PRIMARY KEY("id");

-- ROUNDS
CREATE TABLE "rounds"(
  "id" UUID NOT NULL,
  "acronym_id" UUID NOT NULL,
  "first_answer" UUID NOT NULL,
  "second_answer" UUID NOT NULL,
  "third_answer" UUID NOT NULL,
  "fourth_answer" UUID NOT NULL,
  "fifth_answer" UUID NOT NULL,
  "sixth_answer" UUID NOT NULL,
  "seventh_answer" UUID NOT NULL,
  "eigth_answer" UUID NOT NULL,
  "created_on" TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  "updated_on" TIMESTAMP
);
ALTER TABLE "rounds" ADD CONSTRAINT "rounds_id" PRIMARY KEY("id");
ALTER TABLE "rounds" ADD CONSTRAINT "rounds_acronym_fk"
  FOREIGN KEY("acronym_id") REFERENCES "acronyms"("id") ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE "rounds" ADD CONSTRAINT "rounds_first_answer_fk"
  FOREIGN KEY("first_answer") REFERENCES "answers"("id") ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE "rounds" ADD CONSTRAINT "rounds_second_answer_fk"
  FOREIGN KEY("second_answer") REFERENCES "answers"("id") ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE "rounds" ADD CONSTRAINT "rounds_third_answer_fk"
  FOREIGN KEY("third_answer") REFERENCES "answers"("id") ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE "rounds" ADD CONSTRAINT "rounds_fourth_answer_fk"
  FOREIGN KEY("fourth_answer") REFERENCES "answers"("id") ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE "rounds" ADD CONSTRAINT "rounds_fifth_answer_fk"
  FOREIGN KEY("fifth_answer") REFERENCES "answers"("id") ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE "rounds" ADD CONSTRAINT "rounds_sixth_answer_fk"
  FOREIGN KEY("sixth_answer") REFERENCES "answers"("id") ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE "rounds" ADD CONSTRAINT "rounds_seventh_answer_fk"
  FOREIGN KEY("seventh_answer") REFERENCES "answers"("id") ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE "rounds" ADD CONSTRAINT "rounds_eigth_answer_fk"
  FOREIGN KEY("eigth_answer") REFERENCES "answers"("id") ON DELETE CASCADE ON UPDATE CASCADE;

-- GAMES
CREATE TABLE "games"(
  "id" UUID NOT NULL,
  "mode" VARCHAR NOT NULL,
  "first_round" UUID NOT NULL,
  "second_round" UUID NOT NULL,
  "third_round" UUID NOT NULL,
  "created_on" TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  "updated_on" TIMESTAMP
);
ALTER TABLE "games" ADD CONSTRAINT "games_id" PRIMARY KEY("id");
ALTER TABLE "games" ADD CONSTRAINT "games_first_round_fk"
  FOREIGN KEY("first_round") REFERENCES "rounds"("id") ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE "games" ADD CONSTRAINT "games_second_round_fk"
  FOREIGN KEY("second_round") REFERENCES "rounds"("id") ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE "games" ADD CONSTRAINT "games_third_round_fk"
  FOREIGN KEY("third_round") REFERENCES "rounds"("id") ON DELETE CASCADE ON UPDATE CASCADE;
