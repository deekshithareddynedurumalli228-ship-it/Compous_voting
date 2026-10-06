package com.campusvote.dto; import java.util.List; public record VoteRequest(Long studentId,List<Long> candidateIds){}
