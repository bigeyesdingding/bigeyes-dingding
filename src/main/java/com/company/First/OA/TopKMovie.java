package com.company.First.OA;


import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

class Movie
{
    int movieId;
    float rating;
    List<Movie> similarMovies;
}

public class TopKMovie {
    public List<Movie> getNearest(Movie movie, int k){
        List<Movie> res = new ArrayList<>();
        if(movie == null){
            return  res;
        }
        PriorityQueue<Movie> pq = new PriorityQueue<>(
                (m1, m2)->Float.compare(m1.rating, m2.rating)
        );
        //dfs
        dfs(pq,movie, k);

        while(!pq.isEmpty()){
            res.add(pq.poll());
        }
        return res;

    }
    private void dfs(PriorityQueue<Movie> pq, Movie movie, int k){
        if(movie == null){
            return;
        }

        pq.offer(movie);
        if(pq.size()>k){
            pq.poll();
        }

        for(Movie m: movie.similarMovies){
            dfs(pq, m, k);
        }
    }
}
