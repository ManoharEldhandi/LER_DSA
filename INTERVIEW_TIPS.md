# Interview Tips -- Everything Beyond DSA

This file covers the stuff that most people ignore and then regret.
Solving the problem is maybe 50% of what gets you hired. The other 50%
is communication, behavioral answers, system design awareness, and
how you present yourself.


---


## SECTION 1: Before the Interview Day


### Resume Tips for FAANG

- Keep it to one page. No exceptions.
- Lead each bullet with a strong action verb: "Designed", "Implemented",
  "Optimized", "Reduced", "Built".
- Quantify everything: "Reduced API response time by 40%" is better than
  "Improved API performance."
- List relevant projects prominently. Even personal/college projects count if
  they show real engineering work.
- Skills section: List Java, Python, SQL, Git, and any frameworks you know.
  Don't list "Microsoft Word."
- At Cisco as a trainee, frame your work as impact: "Developed internal tool
  that automated X, saving Y hours per week for the team."


### How to Apply

- Apply directly on the company career page AND get a referral.
- Referrals are the single most effective way to get an interview at FAANG.
- Use LinkedIn to connect with engineers at your target companies.
  Send a polite message asking if they would refer you.
- Apply to multiple companies. Don't put all eggs in one basket.
- Target companies: Google, Amazon, Meta, Microsoft, Apple, Netflix,
  plus strong mid-tier companies like Uber, Airbnb, Stripe, Atlassian.


### Mock Interview Practice

In the one-month sprint, start light mock practice in Week 3 and do full
45-minute mocks in Week 4. Here are your options:

1. Pramp (free) -- https://www.pramp.com
   Peer-to-peer mock interviews. Great practice.

2. Interviewing.io (free for practice) -- https://interviewing.io
   Anonymous mock interviews with real engineers.

3. Friends / study partners
   Find someone at your level and interview each other.

4. Record yourself
   Solve a problem on a whiteboard or screen share while explaining
   out loud. Watch the recording. You will cringe and learn a lot.

Do at least 3 mocks during the sprint, then keep going until you have 8-10
mock interviews before your real interviews.


---


## SECTION 2: Technical Interview Structure (What to Expect)


### Typical FAANG Interview Pipeline

1. Online Assessment (OA): 1-2 coding problems, 60-90 minutes
2. Phone Screen: 1 coding problem with an engineer, 45-60 minutes
3. Onsite (Virtual or In-Person): 4-5 rounds
   - 2-3 Coding rounds (DSA problems)
   - 1 System Design round (for experienced roles, lighter for new grads)
   - 1 Behavioral round

For entry-level / new grad roles, system design is often replaced with
another coding round or a "coding + design" hybrid.


### Time Management During a Coding Round

Total time: ~45 minutes
- First 5 minutes: Read problem, ask clarifying questions
- Next 5 minutes: Discuss approach, get agreement from interviewer
- Next 20-25 minutes: Code the solution
- Next 5 minutes: Test with examples, fix bugs
- Last 5 minutes: Discuss complexity, optimizations, follow-ups

If you spend more than 10 minutes without writing code, you are taking
too long on the planning phase. Start coding even if your approach is
brute force -- you can optimize as you go.


### What Interviewers Actually Evaluate

1. Problem solving: Can you break down the problem and find an approach?
2. Coding ability: Can you write clean, correct code?
3. Communication: Can the interviewer follow your thought process?
4. Testing: Do you verify your solution and handle edge cases?
5. Optimization: Can you analyze and improve time/space complexity?

You do NOT need to solve every problem perfectly. Many candidates get
offers after solving 1.5 out of 2 problems, or getting the approach
right but having a small bug. The bar is "would I want to work with
this person?"


---


## SECTION 3: Behavioral Interview (Don't Skip This)

FAANG companies care deeply about behavioral questions, especially Amazon
(their Leadership Principles are legendary). Prepare 5-6 stories from
your experience.


### The STAR Method

Every behavioral answer should follow this structure:

- **Situation:** Set the context. Where were you? What was the project?
- **Task:** What was your specific responsibility or challenge?
- **Action:** What did YOU do? (Not the team -- YOU.)
- **Result:** What was the outcome? Quantify if possible.


### Stories You Should Prepare

Prepare one story for each of these themes. The same story can cover
multiple themes.

1. A time you faced a difficult technical challenge
2. A time you disagreed with a teammate or manager
3. A time you failed or made a mistake
4. A time you went above and beyond
5. A time you had to learn something new quickly
6. A time you helped a teammate succeed

For each story, also prepare a "what would you do differently?" reflection.


### Amazon Leadership Principles (The Most Important Ones)

If you are interviewing at Amazon, you MUST know these:

- Customer Obsession: Start with the customer and work backwards
- Ownership: Think long-term, act on behalf of the entire company
- Bias for Action: Speed matters. Many decisions are reversible.
- Dive Deep: Leaders operate at all levels, stay connected to details
- Deliver Results: Focus on the key inputs, deliver with quality


### Example Behavioral Answer

"Tell me about a time you faced a technical challenge."

"At Cisco, I was working on [project name] and we needed to integrate
a new API endpoint that had inconsistent response formats across different
regions. The challenge was that our existing parser assumed a fixed schema.

My responsibility was to redesign the parsing layer to handle multiple
formats. I researched the different response structures, identified the
common fields, and built an adapter pattern that normalized the data
before it reached our business logic.

The result was that we reduced parsing errors by 90% and the system
handled all regional formats without any manual intervention. The approach
was later adopted by two other teams in the organization."


---


## SECTION 4: System Design (Light Intro)

For entry-level roles, you probably won't get a full system design round.
But having basic awareness shows maturity and helps in coding rounds too
(when the interviewer asks follow-up questions).


### Concepts to Know at a High Level

1. **Client-Server Architecture:** How web requests flow from browser to
   server to database and back.

2. **Databases:** SQL vs NoSQL. When to use each. What an index is.
   PostgreSQL, MySQL, MongoDB, Redis.

3. **Caching:** Why caching speeds things up. Redis, Memcached.
   Cache invalidation strategies (TTL, write-through, write-behind).

4. **Load Balancing:** Distributing traffic across multiple servers.
   Round-robin, least connections, consistent hashing.

5. **APIs:** REST vs GraphQL. HTTP methods. Status codes.
   What makes a good API design.

6. **Message Queues:** Kafka, RabbitMQ. Why asynchronous processing
   matters. Producer-consumer pattern.

7. **Scalability:** Vertical vs horizontal scaling. Sharding. Replication.


### If Asked "Design a URL Shortener" or Similar

Follow this framework:
1. Clarify requirements (functional and non-functional)
2. Estimate scale (how many users? how many requests per second?)
3. Define the API (endpoints, request/response format)
4. Design the data model (what tables? what fields?)
5. Draw the high-level architecture (client -> load balancer -> servers -> DB)
6. Discuss tradeoffs (consistency vs availability, SQL vs NoSQL)


### Resources for System Design

- "Designing Data-Intensive Applications" by Martin Kleppmann (THE book)
- System Design Primer (GitHub): https://github.com/donnemartin/system-design-primer
- ByteByteGo (YouTube channel by Alex Xu)


---


## SECTION 5: Day-of-Interview Checklist


### The Night Before

- [ ] Review your prepared behavioral stories (STAR format)
- [ ] Review your top 5 strongest DSA patterns
- [ ] Prepare your "tell me about yourself" answer (2 minutes max)
- [ ] Test your internet, webcam, microphone
- [ ] Have a glass of water ready
- [ ] Get 7-8 hours of sleep -- seriously


### 30 Minutes Before

- [ ] Close all unnecessary tabs and applications
- [ ] Open your IDE or text editor (whatever the interview uses)
- [ ] Have a blank notepad ready for quick notes
- [ ] Take a few deep breaths. You have prepared for this.


### Your "Tell Me About Yourself" (Template)

"Hi, I'm Manohar. I'm currently a software engineer trainee at Cisco,
where I've been working on [brief description of your work]. I'm
passionate about backend development and machine learning, and I've
been deeply focused on data structures and algorithms for the past
few months. I'm looking for a role where I can contribute to building
scalable systems and continue growing as an engineer."

Keep it under 90 seconds. Hit these points:
1. Who you are (name, current role)
2. What you do (brief, relevant work)
3. What you are passionate about
4. Why you are here (what you are looking for)


### During the Interview -- Do's and Don'ts

DO:
- Ask clarifying questions before coding
- Explain your approach before writing code
- Talk through your thought process as you code
- Test your solution with examples
- Admit when you are stuck and explain what you are thinking
- Be polite and enthusiastic

DON'T:
- Code in silence for long periods
- Jump straight to coding without discussing approach
- Argue with the interviewer
- Panic if you get stuck -- take a breath and think
- Lie about your experience
- Say "I don't know" without trying -- say "I'm not sure, but my
  intuition is..." and then reason through it


---


## SECTION 6: After the Interview

- Send a thank-you email to your recruiter within 24 hours
- If you get rejected, ask for feedback (many companies provide it)
- Log what went well and what didn't -- use it to improve
- If you solved the problem but still got rejected, it was probably
  communication or behavioral. Work on those areas.
- Keep practicing. Most people don't get their dream job on the
  first try. Consistency beats talent.


---


## SECTION 7: Mindset

The biggest thing that separates people who crack FAANG from those who
don't is NOT intelligence. It is consistency and the ability to handle
rejection.

- Some people solve 500 LeetCode problems and still get rejected.
  Because they solved without understanding.
- Some people solve 200 problems and get multiple offers. Because they
  understood every pattern deeply.

Quality over quantity. Every single time.

When you feel stuck or demotivated:
1. Go back to basics. Solve an easy problem. Get a win.
2. Remember why you started. You have 5 months. That is plenty of time.
3. Talk to someone who has been through this process.
4. Take a day off if you need to. Burnout kills preparation faster
   than anything else.

You are already ahead of most people because you have a plan and you
are executing it. Keep going.
