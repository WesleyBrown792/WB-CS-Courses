#define MAXHISTORY 10

void init_history(void);
void add_history(char *cmd, int exitStatus);
void clear_history(void);
void print_history(int firstSequenceNumber);