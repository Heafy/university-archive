Require Import Nat.

Lemma cero_neutro: forall n, n + 0 = n.
Proof.
  induction n.
  simpl.
  trivial.
  rewrite <- IHn.
  simpl.
  rewrite IHn.
  rewrite IHn.
  trivial.
Qed.

Lemma distr_suma: forall n m l, (n + m) + l = n + (m + l).
Proof.
  induction n.
  intros.
  simpl.
  trivial.
  simpl.
  intros.
  rewrite IHn.
  trivial.
Qed.

Lemma suc_suma: forall n m, S (n + m) = n + S m. 
Proof.
  induction n.
  simpl.
  trivial.
  intros.
  simpl.
  rewrite IHn.
  trivial.
Qed.
  
Lemma conm_suma: forall n m, n + m = m + n.
Proof.
  induction n.
  simpl.
  intros.
  rewrite cero_neutro.
  trivial.
  intros.
  simpl.
  rewrite IHn.
  rewrite suc_suma.
  trivial.
Qed.

Definition prop1 := forall A B : Prop, A /\ B -> B \/ B.
Theorem hola: prop1.
Proof.
  unfold prop1.
  intros.
  destruct H.
  left.
  trivial.
Qed.

Inductive par : nat -> Prop:=
| par_0 : par 0
| par_n : forall n, par n -> par(S (S n)).

Compute (par 3).

Example par_2: par 2.
Proof.
  apply par_n.
  apply par_0.
Qed.

Theorem par_suma: forall x, par(x + x).
Proof.
  intros.
  induction x.
  apply par_0.
  simpl.
  rewrite <- suc_suma.
  apply par_n.
  trivial.
Qed.

Theorem suma_pares: forall n m, par n -> par m -> par(n + m).
Proof.
  intros.
  induction H, H0.
  simpl.
  apply par_0.
  simpl.
  apply par_n.
  trivial.
  simpl.
  apply par_n.
  trivial.
  simpl.
  apply par_n.
  trivial.
Qed.

Fixpoint par_bool (n:nat) : bool :=
  match n with
  | 0 => true
  | S n => match (par_bool n) with
           | true => false
           | _ => true
           end
  end.
                         
Inductive le (n:nat) : nat -> Prop :=
  | le_n : n <= n
  | le_S : forall m:nat, n <= m -> n <= S m

where "n <= m" := (le n m) : nat_scope.

Inductive lista (A:Type): Type :=
| empty: lista A
| cons: A -> lista A -> lista A.

Fixpoint suma_l (l: lista nat) : nat :=
  match l with
  |empty _ => 0
  |cons _ n l => n + suma_l l
  end.

Fixpoint concat (A: Type) (l1 l2: lista A) : (lista A) :=
  match l1 with
  |empty _ => l2
  |cons _ a l3 => cons A a (concat A l3 l2)
  end.

Theorem chafa: forall l1 l2, suma_l(concat nat l1 l2) = suma_l(l1) + suma_l(l2).
Proof.
  induction l1.
  simpl.
  trivial.
  intros.
  simpl.
  rewrite IHl1.
  rewrite distr_suma.
  trivial.
Qed.