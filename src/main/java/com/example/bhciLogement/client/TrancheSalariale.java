package com.example.bhciLogement.client;

public enum TrancheSalariale{
    TRANCHE1(new String("100 000 - 300 000")) {
        @Override
        public String toString() {
                return "100 000 - 300 000";
                }
        },
    TRANCHE2(new String("300 000 - 500 000")) {
        @Override
        public String toString() {
            return "300 000 - 500 000";
        }
    },TRANCHE3 (new String("500 000 - 1000 000")) {
        @Override
        public String toString() {
            return "500 000 - 1000 000";
        }
    },TRANCHE4(new String("1 000 000 et plus")) {
        @Override
        public String toString() {
            return "1 000 000 et plus";
        }
    };


    TrancheSalariale(String s) {
    }
}
