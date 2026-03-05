Sabotage Code Explanation

The sabotage code crashes because the TextView variable is explicitly set to null

TextView counterDisplay = null; counterDisplay.setText(“0”);

Since counterDisplay does not reference an actual TextView object, calling setText() on it triggers a NullPointerException. In Android, UI elements must be initialized using findViewById() (after setContentView()) so the variable points to a real view from the layout.

Once the TextView is properly initialized, setText() works normally.
