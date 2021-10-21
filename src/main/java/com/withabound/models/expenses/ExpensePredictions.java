package com.withabound.models.expenses;

import java.util.Map;
import lombok.Getter;

@Getter
public class ExpensePredictions {
  private Map<String, Double> expenseTypePredictionScores;

  private Map<String, Double> taxCategoryPredictionScores;
}
