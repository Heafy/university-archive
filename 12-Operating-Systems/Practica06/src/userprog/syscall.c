#include "userprog/syscall.h"
#include <stdio.h>
#include <syscall-nr.h>
#include "threads/interrupt.h"
#include "threads/thread.h"

static void syscall_handler (struct intr_frame *);

void
syscall_init (void) 
{
  intr_register_int (0x30, 3, INTR_ON, syscall_handler, "syscall");
}

static void
syscall_handler (struct intr_frame *f UNUSED) 
{
  // PRACTICA 06
    uint32_t* esp = f -> esp;
    uint32_t syscall = *esp;
    esp++;

    switch (syscall) {
      case SYS_WRITE: {
        int fd = *esp;
        esp++;
        void* buffer = (void*)*esp;
        esp++;
        unsigned int size = *esp;
        putbuf(buffer, size);
        break;
      }
      case SYS_EXEC:{
        f->eax = process_execute((char*) *esp);
        break;
      }
      case SYS_WAIT:{
        f->eax = process_wait((int) *esp);
        break;
      }
      case SYS_EXIT: {
        int status = (int) * esp;
        struct thread *t = thread_current();
        struct thread *aux_struct_father = t->father;
        if(aux_struct_father != NULL){
          struct list_elem *sons;
          struct auxstruct *aux_struct_1 = NULL;
          for(sons = list_begin(&aux_struct_father->sons); sons != list_end(&aux_struct_father->sons); sons = list_next(sons)){
            struct auxstruct *aux_struct_2 = list_entry(sons,struct auxstruct,son_elem);
            if(aux_struct_2->id == t->tid && aux_struct_2->exit_stat != -1){
              aux_struct_1 = aux_struct_2;
              aux_struct_1->exit_stat = status;
            }
          }
        }
        thread_exit();
        break;
        }
    }
}
