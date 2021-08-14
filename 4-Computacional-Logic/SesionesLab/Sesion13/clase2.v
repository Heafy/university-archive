Theorem suma_0: forall n:nat, n + 0 = n.
Proof.
  induction n.
  reflexivity.
  rewrite <- IHn.
  simpl.
  rewrite IHn.
  rewrite IHn.
  trivial.
Qed.

Theorem and: forall A B: Prop, A -> B -> A /\ B.
Proof.
  intros.
  split.
  trivial.
  trivial.
Qed.
