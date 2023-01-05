export default interface User  {
  uid: string;
  email: string;
  emailVerified: boolean;
  displayName: string;
  disabled: boolean;
  providers: {
    uid: string;
    displayName: string;
    email: string;
    providerId: string;
  }[];
  tokensValidAfterTimestamp: number;
  userMetadata: {
    creationTimestamp: number;
    lastSignInTimestamp: number;
    lastRefreshTimestamp: number;
  };
  customClaims: {};
};