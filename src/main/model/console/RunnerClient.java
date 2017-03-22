package console;

class RunnerClient {
    private Runner test = new Runner();
    private String s0 = test.s0;
    private String s1 = "1 - Найти книгу" +new Runner().s00+ s0;
    int deep1 = 0;
    int deep2;


    public void run() {
        while (deep1 < 1) {
            deep2 = test.checkNumbers(test.enterNumber(s1), 1);
            if (deep2 == 2){
                deep1++;
            }
            while (deep2 < 2) {
                deep2 = test.seachBook(deep2);
            }
        }
    }
}
