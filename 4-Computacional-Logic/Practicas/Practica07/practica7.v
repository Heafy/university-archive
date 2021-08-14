Section stack.

  (* Definición de stack *)
  Inductive stack: Type :=
  | empty: stack
  | push: nat -> stack -> stack.

  (* Definición de la operación pop que toma un stack y lo devuelve sin el elemento del tope *)
  Definition pop (s:stack) : stack :=
    match s with
    | empty => empty
    | push _ s' => s'
    end.

  (* Función que nos regresa el elemento al tope de stack si existe y None en otro caso *)
  Definition top (s:stack) : (option nat) :=
    match s with
    | empty => None
    | push n _ => Some n
    end.

  (* Concatenación de stacks *)
  Fixpoint concatenate (s1 s2: stack) : stack :=
    match s1 with
    | empty => s2
    | push n s1' => push n (concatenate s1' s2)
    end.

  (* Notación para poder usar ++ en la concatenación *)
  Notation " x ++ y " := (concatenate x y).

  (* Devuelve la reversa de un stack *)
  Fixpoint reverse (s: stack) : stack :=
    match s with
    | empty => empty
    | push n s' => (reverse s') ++ (push n empty)
    end.
  
  (* AQUÍ VAN SUS PRUEBAS *)
  Example ejercicio1: pop(pop(pop(empty))) = empty.
   simpl.
   reflexivity.
   Qed.
  
  Example ejercicio2: forall x:nat, pop(push x (pop (push x empty))) = empty.
   simpl.
   reflexivity.
   Qed.
  Proposition ejercicio3: forall (s:stack) (x:nat) , top s = Some x -> s <> empty.
    intros.
    assert (top s <> top empty ) as l1.
    simpl.
    rewrite H.
    discriminate.
    red.
    intros.
    assert (top s = top empty) as l2.
    rewrite H0.
    reflexivity.
    contradiction.
    Qed.
  Theorem ejercicio4: forall (s:stack) (x:nat), pop (push x s) = s.
    intros.
    simpl.
    reflexivity.
    Qed.
  Lemma ejercicio5: forall s:stack, s ++ empty = s.
    intros.
    induction s.
    simpl.
    reflexivity.
    simpl.
    rewrite IHs.
    reflexivity.
    Qed.

  Theorem ejercicio6: forall n:nat, reverse(push n empty) = push n empty.
    intros.
    simpl.
    reflexivity.
    Qed.
        
  Lemma ejercicio7: forall s1 s2 s3:stack, (s1 ++ s2) ++ s3 = s1 ++ (s2 ++ s3).
    intros.
    induction s1.
    simpl.
    reflexivity.
    simpl.
    rewrite IHs1.
    reflexivity.
    Qed. 
  Theorem ejercicio8: forall s1 s2:stack, reverse (concatenate s1 s2) = concatenate (reverse s2) (reverse s1).
    intros.
    induction s1.
    simpl.
    rewrite -> ejercicio5.
    reflexivity.     
    simpl.
    rewrite -> IHs1.
    remember (push n empty) as s3.
    rewrite -> ejercicio7.
    reflexivity.
    Qed.     
              
End stack.