package org.gerdenmaintenance.domain.usecase.mowthelawn.entity;

import lombok.Getter;

@Getter
public enum Orientation {
    N() {
        @Override
        public Orientation orientationToTheRight() {
            return E;
        }

        @Override
        public Orientation orientationToTheLeft() {
            return W;
        }
    }, E() {
        @Override
        public Orientation orientationToTheRight() {
            return S;
        }

        @Override
        public Orientation orientationToTheLeft() {
            return N;
        }
    }, S() {
        @Override
        public Orientation orientationToTheRight() {
            return W;
        }

        @Override
        public Orientation orientationToTheLeft() {
            return E;
        }
    },
    W() {
        @Override
        public Orientation orientationToTheRight() {
            return N;
        }

        @Override
        public Orientation orientationToTheLeft() {
            return S;
        }
    };

    public abstract Orientation orientationToTheRight();

    public abstract Orientation orientationToTheLeft();

}
