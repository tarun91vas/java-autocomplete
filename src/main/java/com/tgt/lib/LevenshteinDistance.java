package com.tgt.lib;

public class LevenshteinDistance {
    public int distance(String s1, String s2){
        int dist[][]=new int[s1.length()+1][s2.length()+1];

        for(int i=0;i<=s1.length();i++)
            dist[i][0]=i;

        for(int j=1;j<=s2.length();j++)
            dist[0][j]=j;

        for(int i=1;i<=s1.length();i++){
            for(int j=1;j<=s2.length();j++){
                int u=(s1.charAt(i-1)==s2.charAt(j-1)?0:1);
                dist[i][j]=Math.min(
                        dist[i-1][j]+1,
                        Math.min(
                                dist[i][j-1]+1,
                                dist[i-1][j-1]+u
                        )
                );
            }
        }
        return dist[s1.length()][s2.length()];
    }

    public static void main(String[] args) {
        LevenshteinDistance levenshteinDistance = new LevenshteinDistance();
        String s1 = "gretness";
        String s2 = "greatness";
        System.out.println(levenshteinDistance.distance(s1, s2));
    }
}
