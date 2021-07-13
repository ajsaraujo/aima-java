package aima.core.search.csp.studyplan.cases;

import aima.core.search.csp.CSP;
import aima.core.search.csp.studyplan.StudyPlanCSP;
import aima.core.search.csp.studyplan.models.DayTime;
import aima.core.search.csp.studyplan.models.StudyBlock;
import aima.core.search.csp.studyplan.models.Subject;
import aima.core.search.csp.studyplan.models.Time;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public abstract class Case {
    private ArrayList<Subject> subjects = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public List<Subject> getSubjects() {
        return Collections.unmodifiableList(subjects);
    }

    public void readStudyGoals() {
        System.out.println("Tempo de Estudo");

        for (Subject subject : subjects) {
            int numOfStudyBlocks = readNumOfStudyBlocks(subject.name);

            for (int i = 0; i < numOfStudyBlocks; i++) {
                System.out.println(getOrdinal(i + 1) + " bloco de estudo:");
                Time studyBlock = readStudyBlock();
                subject.addStudyBlock(studyBlock);
            }
        }
    }

    public CSP<StudyBlock, DayTime> toCSP() {
        StudyPlanCSP csp = new StudyPlanCSP();

        subjects.forEach(csp::addSubject);

        csp.loadConstraints();

        return csp;
    }

    protected void addSubject(Subject subject) {
        subjects.add(subject);
    }

    private Time readStudyBlock() {
        while (true) {
            System.out.println("Escolha uma opção de 1 a 8:");
            System.out.println("1) 30 minutos");
            System.out.println("2) 1 hora");
            System.out.println("3) 1 hora e 30 minutos");
            System.out.println("4) 2 horas");
            System.out.println("5) 2 horas e 30 minutos");
            System.out.println("6) 3 horas");
            System.out.println("7) 3 horas e 30 minutos");
            System.out.println("8) 4 horas");

            int answer = scanner.nextInt();
            int LOWER_BOUND = 1;
            int UPPER_BOUND = 8;

            if (LOWER_BOUND <= answer && answer <= UPPER_BOUND) {
                int totalMinutes = answer * 30;

                int hours = totalMinutes / 60;
                int minutes = totalMinutes % 60;

                return new Time(hours, minutes);
            }

            System.out.println("Você deve escolher um número entre " + LOWER_BOUND + " e " + UPPER_BOUND + ". Por favor, tente novamente.");
        }

    }

    private int readNumOfStudyBlocks(String subjectName) {
        int numOfStudyBlocks = 0;
        int MIN_STUDY_BLOCKS = 1;
        int MAX_STUDY_BLOCKS = 10;

        while (true) {
            System.out.println("Quantos blocos de estudo para " + subjectName + "?");
            numOfStudyBlocks = scanner.nextInt();

            if (numOfStudyBlocks < MIN_STUDY_BLOCKS || numOfStudyBlocks > MAX_STUDY_BLOCKS) {
                System.out.println("Você deve escolher um número entre " + MIN_STUDY_BLOCKS + " e " + MAX_STUDY_BLOCKS + ". Por favor, tente novamente.");
            } else {
                break;
            }
        }

        return numOfStudyBlocks;
    }

    private String getOrdinal(int i) {
        assert i >= 1 && i <= 10;

        String[] ordinals = new String[]{"Primeiro", "Segundo", "Terceiro", "Quarto", "Quinto", "Sexto", "Sétimo", "Oitavo", "Nono", "Décimo"};
        return ordinals[i - 1];
    }
}
